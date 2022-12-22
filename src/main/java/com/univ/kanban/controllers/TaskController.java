package com.univ.kanban.controllers;

import com.univ.kanban.models.Kanban;
import com.univ.kanban.models.KanbanColumn;
import com.univ.kanban.models.Task;
import com.univ.kanban.services.ColumnService;
import com.univ.kanban.services.KanbanService;
import com.univ.kanban.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final ColumnService columnService;

    @PutMapping("/{id}/column/{id_column}")
    public void changeColumn(@PathVariable Long id, @PathVariable Long id_column) {
        Task task = taskService.findById(id);
        KanbanColumn column = columnService.findById(id_column);
        task.setColumn(column);
        taskService.save(task);
        Kanban kanban = column.getKanban();
        Long kanban_id = kanban.getId();
    }

}
