package com.univ.kanban.repositories;

import java.util.Optional;
import java.util.Set;

import com.univ.kanban.models.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

  Optional<User> findByEmail(String email);

  @Query(value = "SELECT * FROM user WHERE id NOT IN ("
  + "SELECT members_id FROM user_kanbans WHERE kanbans_id = ?1) AND id NOT IN (" 
  + "SELECT user_id FROM kanban_request WHERE kanban_id = ?1)"
  , nativeQuery = true)
  Set<User> findUserInNotInKanban(Long kanban);

  Optional<User> findById(Long id);
}
