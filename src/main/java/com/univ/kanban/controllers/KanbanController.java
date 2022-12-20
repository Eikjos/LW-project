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

@Controller
@RequestMapping("/kanbans")
public class KanbanController {

    private final KanbanService kanbanService;

    public KanbanController(KanbanService kanbanService) {
        this.kanbanService = kanbanService;
    }

    @GetMapping("/{id}")
    public String GetKanban(@PathVariable(value = "id") Long id, Model model) {
        Kanban kanban = kanbanService.findById(id);
        model.addAttribute("kanban", kanban);
        return "kanban/kanban";
    }

    @GetMapping("/create")
    public String create(Model model, Authentication authenticate) {
        User user = (User) authenticate.getDetails();
        KanbanDto dto = new KanbanDto(user);
        model.addAttribute("kanbanDto", dto);
        return "kanban/create";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute("kanbanDto") @Valid KanbanDto kanbanDto,
            HttpServletRequest request,
            Errors errors) {
        Kanban kanban = kanbanService.create(kanbanDto);
        return "redirect:/kanbans/" + kanban.getId();
    }
}
