package com.univ.kanban.repositories;

import java.util.Optional;

import com.univ.kanban.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

  Optional<User> findByEmail(String email);
}
