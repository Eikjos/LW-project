package com.univ.kanban.services;

import com.univ.kanban.models.KanbanColumn;
import com.univ.kanban.models.Task;
import com.univ.kanban.repositories.ColumnRepository;
import com.univ.kanban.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ColumnService {

    private final ColumnRepository columnRepository;

    public KanbanColumn findById(Long id) {
        return columnRepository.findById(id).orElse(null);
    }

}
