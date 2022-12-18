package com.univ.kandan.controllers;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import com.univ.kandan.service.UsersService;
import com.univ.kandan.model.User;

@Component
@Controller
public class AuthentificationController implements AuthenticationProvider {

  private final UsersService usersService;

  public AuthentificationController(UsersService usersService) {
    this.usersService = usersService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    var email = authentication.getName();
    var password = authentication.getCredentials().toString();

    User user = usersService.authenticate(email, password);

    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
        email,
        password);
    token.setDetails(user);
    return token;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
