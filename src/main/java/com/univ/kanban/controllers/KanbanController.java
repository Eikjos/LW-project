package com.univ.kanban.controllers;

import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.univ.kanban.dto.KanbanRequestDto;
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
    public String GetKanban(@PathVariable(value = "id") Long id, Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            User user = usersService.findByEmail(authentication.getName()).orElseThrow();
            model.addAttribute("user", user);
        }

        Kanban kanban = kanbanService.findById(id);
        model.addAttribute("kanban", kanban);
        return "kanban/kanban";
    }

    @GetMapping("/create")
    public String create() {
        return "kanban/create";
    }

    @PostMapping("/{id}/delete")
    public String deleteKanban(@PathVariable(value = "id") Long id) {
        Kanban kanban = kanbanService.findById(id);
        kanbanService.delete(kanban);

        return "redirect:/";
    }

    @GetMapping("/{id}/invite-user")
    public String inviteUser(@PathVariable(value = "id") Long id, Model model) {
        Set<User> users = usersService.userInNotkanban(id);
        KanbanRequestDto kanbanRequest = new KanbanRequestDto(id);
        model.addAttribute("users", users);
        model.addAttribute("kanbanRequestDto", kanbanRequest);
        return "kanban/invite";
    }
}
