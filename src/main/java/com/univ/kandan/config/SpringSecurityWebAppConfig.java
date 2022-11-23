package com.univ.kandan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.univ.kandan.Controllers.AuthentificationController;

@Configuration
@EnableWebSecurity
public class SpringSecurityWebAppConfig extends WebSecurityConfigurerAdapter {

  private final AuthentificationController authentificationController;

  @Autowired
  public SpringSecurityWebAppConfig(AuthentificationController authentificationController) {
    this.authentificationController = authentificationController;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authentificationController);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests()
        .antMatchers("/css/*", "/fonts/*", "/img/*", "/js/*", "/favicon.png").permitAll()
        .antMatchers("/login", "/").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .and()
        .logout();
  }
}
