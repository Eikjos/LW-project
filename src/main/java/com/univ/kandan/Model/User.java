package com.univ.kandan.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Column;

@Entity
public class User implements Comparable<User> {

  @Id
  @GeneratedValue
  @Column(name = "Id")
  private Long id;

  @Column(name = "Nom")
  private String nom;

  @Column(name = "Prénom")
  private String prenom;

  @Column(name = "Email")
  private String email;

  @Column(name = "Password")
  private String password;

  @ManyToMany
  @JoinTable
  private Set<Kanban> kanbans;

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

  public void SetKanbans(Set<Kanban> kanbans) {
    this.kanbans = kanbans;
  }

  public Long getId() {
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

  public Set<Kanban> getKanbans() {
    return kanbans;
  }

  @Override
  public int compareTo(User obj) {
    return this.id.compareTo(obj.id);
  }
}
