package com.univ.kandan.Repository;

import java.util.Set;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.univ.kandan.Model.Kanban;

@Repository
public interface KanbanRepository extends CrudRepository<Kanban, UUID> {
    
    Set<Kanban> findAllByIsPublic(boolean isPublic);

}
