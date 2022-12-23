package com.univ.kanban.controllers;

import com.univ.kanban.dto.TaskDto;
import com.univ.kanban.models.KanbanColumn;
import com.univ.kanban.models.Task;
import com.univ.kanban.services.ColumnService;
import com.univ.kanban.services.KanbanService;
import com.univ.kanban.services.TaskService;
import com.univ.kanban.services.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final ColumnService columnService;
    private final KanbanService kanbanService;
    private final UsersService usersService;

    @PutMapping("/{id}/column/{id_column}")
    public void changeColumn(@PathVariable Long id, @PathVariable Long id_column) {
        Task task = taskService.findById(id);
        KanbanColumn column = columnService.findById(id_column);
        task.setColumn(column);
        taskService.save(task);
    }

    @PostMapping(path="/create")
    public String addTask(@ModelAttribute("taskDto") TaskDto taskDto){
        var task = taskService.createTask();
        task.setNom(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        var column = columnService.findById(taskDto.getColumn_id());
        task.setColumn(column);
        task.setOrder(0);
        taskService.save(task);
        var kanban = column.getKanban().getId();
        return "redirect:/kanbans/"+kanban;
    }

    @PostMapping(path="/{id}/modify")
    public String modifyTask(@PathVariable Long id, @ModelAttribute("taskDto") TaskDto taskDto) {
        System.out.print(taskDto.toString());
        var task = taskService.findById(id);
        task.setNom(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        var column = columnService.findById(taskDto.getColumn_id());
        task.setColumn(column);
        var user = usersService.findById(taskDto.getUser_id());
        System.out.print(user.getNom());
        task.setUser(user);
        taskService.save(task);
        var kanban = column.getKanban().getId();
        return "redirect:/kanbans/"+kanban;
    }
}
