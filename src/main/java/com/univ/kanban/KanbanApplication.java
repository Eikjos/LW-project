package com.univ.kanban;

import com.univ.kanban.models.KanbanColumn;
import com.univ.kanban.models.Task;
import com.univ.kanban.repositories.ColumnRepository;
import com.univ.kanban.repositories.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.univ.kanban.models.Kanban;
import com.univ.kanban.models.User;
import com.univ.kanban.repositories.KanbanRepository;
import com.univ.kanban.repositories.UserRepository;

import java.util.HashSet;

@SpringBootApplication
public class KanbanApplication {

    public static void main(String[] args) {
        SpringApplication.run(KanbanApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CommandLineRunner seedApplication(UserRepository userRepository, KanbanRepository kanbanRepository, ColumnRepository columnRepository, TaskRepository taskRepository,
            PasswordEncoder passwordEncoder) {
        return (args) -> {
            loadUserData(userRepository, passwordEncoder);
            loadKanbanData(kanbanRepository, userRepository, columnRepository, taskRepository);
        };
    }

    private void loadUserData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        if (userRepository.count() == 0) {
            User user1 = new User();
            user1.setPrenom("Thomas");
            user1.setNom("Hamelin");
            user1.setEmail("thomas.hamelin@univ-rouen.fr");
            user1.setPassword(passwordEncoder.encode("thomas"));

            User user2 = new User();
            user2.setPrenom("Lucas");
            user2.setNom("Ficker");
            user2.setEmail("lucas.ficker@univ-rouen.fr");
            user2.setPassword(passwordEncoder.encode("lucas"));

            userRepository.save(user1);
            userRepository.save(user2);
        }
    }

    private void loadKanbanData(KanbanRepository kanbanRepository, UserRepository userRepository, ColumnRepository columnRepository, TaskRepository taskRepository) {
        if (kanbanRepository.count() == 0 && userRepository.count() != 0) {
            User user1 = userRepository.findByEmail("thomas.hamelin@univ-rouen.fr").orElseThrow();
            User user2 = userRepository.findByEmail("lucas.ficker@univ-rouen.fr").orElseThrow();
            Kanban kanban1 = new Kanban();
            kanban1.setNom("Projet 1");
            kanban1.setDescription("Ceci est le premier projet de l'application kanban que nous venons de réaliser");
            kanban1.setCreator(user1);
            kanban1.setPublic(true);

            KanbanColumn column = new KanbanColumn();
            column.setNom("truc a faire");
            column.setOrder(1);
            column.setKanban(kanban1);
            column = columnRepository.save(column);

            Task task = new Task();
            task.setNom("Baiser Kevin");
            task.setDescription("Il faut bien baiser le gros Kevin");
            task.setOrder(1);
            task = taskRepository.save(task);
            HashSet<Task> tasks = new HashSet<>();
            tasks.add(task);

            column.setTasks(tasks);
            HashSet<KanbanColumn> columns = new HashSet<>();
            columns.add(column);
            kanban1.setColumns(columns);

            Kanban kanban2 = new Kanban();
            kanban2.setNom("Projet 2");
            kanban2.setDescription("Ceci est le deuxième projet de l'application kanban que nous venons de réaliser");
            kanban2.setCreator(user2);
            kanban2.setPublic(false);

            kanban1 = kanbanRepository.save(kanban1);
            kanban2 = kanbanRepository.save(kanban2);

            kanban1.addMember(user2);
            kanban1.addMember(user1);

            kanban2.addMember(user2);
            kanban2.addMember(user1);

            kanbanRepository.save(kanban1);
            kanbanRepository.save(kanban2);
        }
    }
}
