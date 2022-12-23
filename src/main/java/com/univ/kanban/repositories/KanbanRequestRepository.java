package com.univ.kanban.repositories;


import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.univ.kanban.models.KanbanRequest;
import com.univ.kanban.models.User;

public interface KanbanRequestRepository extends CrudRepository<KanbanRequest, Long>{
    
    Set<KanbanRequest> findAllByUser(User user);

    Optional<KanbanRequest> findById(Long id);
}
