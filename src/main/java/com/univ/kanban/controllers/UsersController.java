package com.univ.kanban.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.univ.kanban.dto.UserDto;
import com.univ.kanban.exceptions.UserAlreadyExistException;
import com.univ.kanban.services.UsersService;

@AllArgsConstructor
@Controller
@RequestMapping("/users")
public class UsersController {

    private final PasswordEncoder passwordEncoder;
    private final UsersService usersService;


    @PostMapping("/save")
    public String register(
            @ModelAttribute("userDto") @Valid UserDto userDto,
            HttpServletRequest request,
            Errors errors) {

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        try {
            usersService.registerUser(userDto);
        } catch (UserAlreadyExistException e) {
            return "redirect:/users/register?error=userAlreadyExist";
        }

        return "redirect:/users/register?success";
    }
}
