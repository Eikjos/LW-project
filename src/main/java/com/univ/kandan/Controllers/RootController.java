package com.univ.kandan.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;
import com.univ.kandan.dto.UserDto;

@Controller
public class RootController {

  @GetMapping("/")
  public String home() {
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
