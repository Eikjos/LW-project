package com.univ.kandan.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;
import com.univ.kandan.dto.UserDto;
import com.univ.kandan.model.Kanban;
import com.univ.kandan.service.KanbanService;

import java.util.Set;

@Controller
public class RootController {

  private final KanbanService kanbanService;

  public RootController(KanbanService kanbanService) {
    this.kanbanService = kanbanService;
  }

  @GetMapping("/")
  public String home(WebRequest request, Model model) {
    String user = request.getRemoteUser();
    Set<Kanban> kanbans = kanbanService.findAllIsPublic(true);
    if (user != null) {
      kanbans.addAll(kanbanService.findAllIsPublic(false));
    }
    model.addAttribute("kanbans", kanbans);
    return "index";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/register")
  public String register(WebRequest request, Model model) {
    UserDto userDto = new UserDto();
    model.addAttribute("userDto", userDto);
    return "register";
  }

}
