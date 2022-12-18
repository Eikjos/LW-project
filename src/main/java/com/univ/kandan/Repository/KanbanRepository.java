package com.univ.kandan.repository;

import java.util.Set;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

import com.univ.kandan.model.Kanban;

public interface KanbanRepository extends CrudRepository<Kanban, UUID> {

    Set<Kanban> findAllByIsPublic(boolean isPublic);

}
