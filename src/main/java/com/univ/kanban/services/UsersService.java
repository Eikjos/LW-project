package com.univ.kanban.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.univ.kanban.dto.UserDto;
import com.univ.kanban.exceptions.UserAlreadyExistException;
import com.univ.kanban.models.Kanban;
import com.univ.kanban.models.User;
import com.univ.kanban.repositories.UserRepository;

import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class UsersService {

    private final UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User registerUser(UserDto model) throws UserAlreadyExistException {
        User user = new User();

        if (userRepository.findByEmail(model.getEmail()).isPresent()) {
            throw new UserAlreadyExistException();
        }

        user.setPrenom(model.getPrenom());
        user.setNom(model.getNom());
        user.setEmail(model.getEmail());
        user.setPassword(model.getPassword());

        return userRepository.save(user);
    }

    public Set<User> userInNotkanban(Long id) {
        return userRepository.findUserInNotInKanban(id);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

}
