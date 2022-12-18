package com.univ.kandan;

import java.util.Collection;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.univ.kandan.Model.Kanban;
import com.univ.kandan.Model.User;
import com.univ.kandan.Repository.KanbanRepository;
import com.univ.kandan.Repository.UserRepository;


@EnableAutoConfiguration
@SpringBootApplication
public class KandanApplication {

	public static void main(String[] args) {
		SpringApplication.run(KandanApplication.class, args);
	}

	@Bean
	public CommandLineRunner SeedApplication(UserRepository userRepository, KanbanRepository kanbanRepository) {
		return (args) -> {
			loadUserData(userRepository);
			loadKanbanData(kanbanRepository, userRepository);
		};
	}

	private void loadUserData(UserRepository userRepository) {
        if (userRepository.count() == 0) {
            User user1 = new User();
            user1.setPrenom("Thomas");
            user1.setNom("Hamelin");
            user1.setEmail("thomas.hamelin@univ-rouen.fr");
            user1.setPassword("azeqsdwxc.2022");

            User user2 = new User();
            user2.setPrenom("Lucas");
            user2.setNom("Ficker");
            user2.setEmail("lucas.ficker@univ-rouen.fr");
            user2.setPassword("azeqsdwxc.2022");

            userRepository.save(user1);
            userRepository.save(user2);
        }
    }

    private void loadKanbanData(KanbanRepository kanbanRepository, UserRepository userRepository) {
        if (kanbanRepository.count() == 0 && userRepository.count() != 0) {
            User user1 = userRepository.findByEmail("thomas.hamelin@univ-rouen.fr").orElseThrow();
            User user2 = userRepository.findByEmail("lucas.ficker@univ-rouen.fr").orElseThrow();
            Kanban kanban1 = new Kanban();
            kanban1.setNom("Projet 1");
            kanban1.setDescription("Ceci est le premier projet de l'application kanban que nous venons de réaliser");
            kanban1.setCreator(user1);
            kanban1.setIsPublic(true);
			TreeSet<User> members = new TreeSet<User>();
            members.add(user1);
            members.add(user2);
            kanban1.setMembers(members);

            Kanban kanban2 = new Kanban();
            kanban2.setNom("Projet 2");
            kanban2.setDescription("Ceci est le deuxième projet de l'application kanban que nous venons de réaliser");
            kanban2.setCreator(user2);
            kanban2.setIsPublic(false);
            kanban1.setMembers(members);

            kanbanRepository.save(kanban1);
            kanbanRepository.save(kanban2);
        }
    }
}
