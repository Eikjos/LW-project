package com.univ.kandan.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidCredentialsException extends AuthenticationException {

  public InvalidCredentialsException(String msg) {
    super(msg);
  }

  public InvalidCredentialsException() {
    super(null);
  }

}
