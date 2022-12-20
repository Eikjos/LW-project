package com.univ.kanban.controllers;

import com.univ.kanban.services.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;
import com.univ.kanban.dto.UserDto;
import com.univ.kanban.models.Kanban;
import com.univ.kanban.models.User;
import com.univ.kanban.services.KanbanService;

import java.util.Set;

@AllArgsConstructor
@Controller
public class RootController {

    private final KanbanService kanbanService;
    private final UsersService usersService;

    @GetMapping("/")
    public String home(Model model, Authentication authenticate) {
        Set<Kanban> kanbans = kanbanService.findAllIsPublic();
        if (authenticate == null) {
            model.addAttribute("kanbans", kanbans);
            return "index";
        }
        User user = usersService.findByEmail(authenticate.getName()).orElse(null);
        if (user != null) {
            kanbans = kanbanService.findAllByUser(user.getId());
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
