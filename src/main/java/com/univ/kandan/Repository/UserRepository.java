package com.univ.kandan.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.univ.kandan.Model.User;;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

  Optional<User> findByEmail(String email);

}
