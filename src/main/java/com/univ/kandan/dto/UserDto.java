package com.univ.kandan.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDto {

  @NotNull
  @NotEmpty
  private String prenom;

  @NotNull
  @NotEmpty
  private String nom;

  @NotNull
  @NotEmpty
  private String email;

  @NotNull
  @NotEmpty
  private String password;

  public String getNom() {
    return nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
