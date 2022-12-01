package com.univ.kandan.model;

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

  public UUID getId() {
    return id;
  }

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
}
