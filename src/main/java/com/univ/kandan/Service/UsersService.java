package com.univ.kandan.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.univ.kandan.dto.UserDto;
import com.univ.kandan.Exceptions.InvalidCredentialsException;
import com.univ.kandan.Exceptions.UserAlreadyExistException;
import com.univ.kandan.Model.User;
import com.univ.kandan.Repository.UserRepository;

@Service
public class UsersService {

  private final PasswordEncoder passwordEncoder;

  private final UserRepository userRepository;

  @Autowired
  public UsersService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User authenticate(String email, String password) {
    User user = userRepository.findByEmail(email).orElse(null);
    if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
      throw new InvalidCredentialsException();
    }
    return user;
  }

  public User registerUser(UserDto model) throws UserAlreadyExistException {
    User user = new User();

    if (userRepository.findByEmail(model.getEmail()).isPresent()) {
      throw new UserAlreadyExistException();
    }

    user.setPrenom(model.getPrenom());
    user.setNom(model.getNom());
    user.setEmail(model.getEmail());
    user.setPassword(passwordEncoder.encode(model.getPassword()));

    return userRepository.save(user);
  }

}
