package com.univ.kandan.Model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
public class User {

  @Id
  @GeneratedValue
  @Column(name = "Id")
  private UUID id;

  @Column(name = "Nom")
  private String nom;

  @Column(name = "Prénom")
  private String prenom;

  @Column(name = "Email")
  private String email;

  @Column(name = "Password")
  private String password;

  public String getPassword() {
    return password;
  }

}
