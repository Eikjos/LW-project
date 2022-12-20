package com.univ.kanban.models;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import lombok.Data;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Data
@Entity
public class Kanban implements Comparable<Kanban> {

  public Kanban() {
    members = new HashSet<User>();
    columns = new HashSet<KanbanColumn>();
  }

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

  @ManyToMany
  @JoinTable(
        name = "user_kanbans",
        joinColumns = {@JoinColumn(name = "kanbans_id")},
        inverseJoinColumns = {@JoinColumn(name = "members_id")}
    )
  private Set<User> members;

  @OneToMany(mappedBy = "kanban", orphanRemoval = true, cascade = {CascadeType.ALL})
  private Set<KanbanColumn> columns;

  // ---

  public void setChildren(Set<KanbanColumn> children) {
    this.columns.clear();
    if (children != null) {
      this.columns.addAll(children);
    }
}

  public void addMember(User user) {
    members.add(user);
  }

  public int compareTo(Kanban kanban) {
    return this.id.compareTo(kanban.id);
  }

  public int hashCode() {
    return this.id.hashCode();
}
}
