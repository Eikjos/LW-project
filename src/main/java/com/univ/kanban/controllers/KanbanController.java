package com.univ.kanban.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.univ.kanban.dto.KanbanDto;
import com.univ.kanban.models.Kanban;
import com.univ.kanban.models.User;
import com.univ.kanban.services.KanbanService;
import com.univ.kanban.services.UsersService;

@Controller
@RequestMapping("/kanbans")
public class KanbanController {

    private final KanbanService kanbanService;
    private final UsersService usersService;

    public KanbanController(KanbanService kanbanService, UsersService usersService) {
        this.kanbanService = kanbanService;
        this.usersService = usersService;
    }

    @GetMapping("/{id}")
    public String GetKanban(@PathVariable(value = "id") Long id, Model model) {
        Kanban kanban = kanbanService.findById(id);
        model.addAttribute("kanban", kanban);
        return "kanban/kanban";
    }

    @GetMapping("/create")
    public String create() {
        return "kanban/create";
    }
}
