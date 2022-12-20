package com.univ.kanban.models;

import java.util.Set;
import java.util.TreeSet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Data
@Entity
public class Kanban implements Comparable<Kanban> {

  @Id
  @GeneratedValue
  @Column(name = "Id")
  private Long id;

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
  private Set<User> members = new TreeSet<>();

  @OneToMany(mappedBy = "kanban", orphanRemoval = true)
  private Set<KanbanColumn> columns;

  // ---

  public void addMember(User user) {
    members.add(user);
  }

  public int compareTo(Kanban kanban) {
    return this.id.compareTo(kanban.id);
  }
}
