package com.univ.kandan.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.univ.kandan.Model.User;

public interface UsersRepository extends CrudRepository<User, UUID> {

  Optional<User> findByEmail(String email);

}
