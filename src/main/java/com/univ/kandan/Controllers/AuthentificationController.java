package com.univ.kandan.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.univ.kandan.Model.User;
import com.univ.kandan.Service.UsersService;

@Component
@Controller
public class AuthentificationController implements AuthenticationProvider {

  private final UsersService usersService;

  @Autowired
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
    return true;
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }
}
