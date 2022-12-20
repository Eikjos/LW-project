package com.univ.kanban.repositories;

import com.univ.kanban.models.KanbanColumn;
import org.springframework.data.repository.CrudRepository;

public interface ColumnRepository extends CrudRepository<KanbanColumn, Long>{
}
