package com.univ.kanban.repositories;

import com.univ.kanban.models.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
