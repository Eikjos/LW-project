package com.univ.kandan.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.univ.kandan.dto.KanbanDto;
import com.univ.kandan.model.Kanban;
import com.univ.kandan.model.User;
import com.univ.kandan.service.KanbanService;

@Controller
@RequestMapping("/kanbans")
public class KanbanController {
    
    private final KanbanService kanbanService;
  
    public KanbanController(KanbanService kanbanService) {
        this.kanbanService = kanbanService;
    }

    @GetMapping("/{id}")
    public String GetKanban(@PathVariable(value = "id")Long id, Model model) {
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
        Errors errors) 
    {
        Kanban kanban = kanbanService.create(kanbanDto);
        return "redirect:/kanbans/"  + kanban.getId();
    }

}
