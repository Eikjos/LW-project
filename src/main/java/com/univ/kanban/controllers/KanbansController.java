package com.univ.kanban.controllers;

import org.modelmapper.internal.Errors;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.univ.kanban.dto.KanbanDto;
import com.univ.kanban.models.Kanban;
import com.univ.kanban.models.User;
import com.univ.kanban.services.KanbanService;
import com.univ.kanban.services.UsersService;

import jakarta.validation.Valid;


@Controller
@RestController
public class KanbansController {
    
    private final KanbanService kanbanService;
    private final UsersService usersService;

    public KanbansController(KanbanService kanbanService, UsersService usersService) {
        this.kanbanService = kanbanService;
        this.usersService = usersService;
    }

    @PostMapping("kanbans/save")
    public Long save(@RequestBody KanbanDto kanbanDto, Authentication authentication) {
        System.out.println(kanbanDto.getColumns().toString());
        User user = usersService.findByEmail(authentication.getName()).orElseThrow();
        Kanban kanban = kanbanService.create(kanbanDto, user);
        return kanban.getId();
    }
}
