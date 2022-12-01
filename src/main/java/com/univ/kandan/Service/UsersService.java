package com.univ.kandan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.univ.kandan.dto.UserDto;
import com.univ.kandan.exceptions.InvalidCredentialsException;
import com.univ.kandan.exceptions.UserAlreadyExistException;
import com.univ.kandan.model.User;
import com.univ.kandan.repository.UsersRepository;

@Service
public class UsersService {

  private final PasswordEncoder passwordEncoder;

  private final UsersRepository usersRepository;

  @Autowired
  public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
    this.usersRepository = usersRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User authenticate(String email, String password) {
    User user = usersRepository.findByEmail(email).orElse(null);
    if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
      throw new InvalidCredentialsException();
    }
    return user;
  }

  public User registerUser(UserDto model) throws UserAlreadyExistException {
    User user = new User();

    if (usersRepository.findByEmail(model.getEmail()).isPresent()) {
      throw new UserAlreadyExistException();
    }

    user.setPrenom(model.getPrenom());
    user.setNom(model.getNom());
    user.setEmail(model.getEmail());
    user.setPassword(passwordEncoder.encode(model.getPassword()));

    return usersRepository.save(user);
  }

}
