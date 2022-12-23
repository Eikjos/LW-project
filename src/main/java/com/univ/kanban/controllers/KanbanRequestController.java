package com.univ.kanban.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.univ.kanban.dto.KanbanRequestDto;
import com.univ.kanban.models.KanbanRequest;
import com.univ.kanban.repositories.KanbanRequestRepository;
import com.univ.kanban.services.KanbanRequestService;
import com.univ.kanban.services.KanbanService;
import com.univ.kanban.services.UsersService;

@Controller
@RequestMapping("/kanban-request")
public class KanbanRequestController {
    
    private final KanbanRequestService kanbanRequestService;
    private final KanbanService kanbanService;
    private final UsersService usersService;

    public KanbanRequestController(KanbanRequestService kanbanRequestService, UsersService usersService, KanbanService kanbanService) {
        this.kanbanRequestService = kanbanRequestService;
        this.kanbanService = kanbanService;
        this.usersService = usersService;
    }

    @PostMapping("/invite")
    public String invite(@ModelAttribute("kanbanRequestDto") KanbanRequestDto kanbanRequestDto) {
        KanbanRequest request = new KanbanRequest();
        request.setKanban(kanbanService.findById(kanbanRequestDto.getKanban()));
        request.setUser(usersService.findById(kanbanRequestDto.getUser()));
        kanbanRequestService.save(request);
        return "redirect:/kanbans/" + kanbanRequestDto.getKanban();
    }

}
