package com.univ.kandan.model;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

public class Kanban {

  @Id
  @GeneratedValue
  @Column(name = "Id")
  private UUID id;

  @Column(name = "Nom")
  private String nom;

  @Column(name = "Description")
  private String description;

  @Column(name = "isPublic")
  private boolean isPublic;

  @ManyToOne(optional = false)
  @Fetch(FetchMode.JOIN)
  private User creator;

  @ManyToMany(mappedBy = "kanbans")
  private Set<User> users;

}
