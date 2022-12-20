package com.univ.kanban.config;

import com.univ.kanban.services.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UsersService usersService;

    // ---

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/css/*", "/fonts/*", "/img/*", "/js/*", "/favicon.png").permitAll()
                        .requestMatchers("/login", "/", "/register", "/users/save", "/kanbans/{id:[0-9]+}").permitAll()
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error")
                        .loginPage("/login"))
                .logout(logout -> logout.logoutSuccessUrl("/"))
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            var user = usersService.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException(email));
            return new User(user.getEmail(), user.getPassword(), Collections.emptyList());
        };
    }
}
