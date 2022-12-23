package com.univ.kanban.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.univ.kanban.dto.KanbanRequestDto;
import com.univ.kanban.models.Kanban;
import com.univ.kanban.models.KanbanRequest;
import com.univ.kanban.repositories.KanbanRequestRepository;
import com.univ.kanban.services.KanbanRequestService;
import com.univ.kanban.services.KanbanService;
import com.univ.kanban.services.UsersService;
import org.springframework.web.bind.annotation.RequestBody;


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

    @PostMapping("/{id}/accept")
    public String accept(@PathVariable(value = "id") Long id) {
        KanbanRequest request = kanbanRequestService.findById(id);
        Kanban kanban = request.getKanban();
        kanban.addMember(request.getUser());
        kanban = kanbanService.save(kanban);
        kanbanRequestService.delete(request);
        return "redirect:/kanbans/" + kanban.getId();
    }

    @PostMapping("/{id}/refuse")
    public String refuse(@PathVariable(value = "id") Long id) {
        KanbanRequest request = kanbanRequestService.findById(id);
        kanbanRequestService.delete(request);
        return "redirect:/";
    }
    

}
