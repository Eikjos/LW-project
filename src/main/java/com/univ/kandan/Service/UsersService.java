package com.univ.kandan.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.univ.kandan.Exceptions.InvalidCredentialsException;
import com.univ.kandan.Model.User;
import com.univ.kandan.Repository.UsersRepository;

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

}
