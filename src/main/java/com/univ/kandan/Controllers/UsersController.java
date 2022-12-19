package com.univ.kandan.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.univ.kandan.dto.UserDto;
import com.univ.kandan.exceptions.UserAlreadyExistException;
import com.univ.kandan.model.User;
import com.univ.kandan.service.UsersService;

@Controller
@RequestMapping("/users")
public class UsersController {

  private final UsersService usersService;

  public UsersController(UsersService usersService) {
    this.usersService = usersService;
  }

  @PostMapping("/save")
  public String register(
      @ModelAttribute("userDto") @Valid UserDto userDto,
      HttpServletRequest request,
      Errors errors) 
  {

    try {
      usersService.registerUser(userDto);
    } catch (UserAlreadyExistException e) {
      return "redirect:/users/register?error=userAlreadyExist";
    }

    return "redirect:/users/register?success";
  }

}
