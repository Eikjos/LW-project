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

}
