package com.univ.kanban.models;

import java.util.Set;
import java.util.TreeSet;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Column;
import lombok.Data;

@Data
@Entity
public class User implements Comparable<User> {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    @Column(name = "Nom")
    private String nom;

    @Column(name = "Prenom")
    private String prenom;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Kanban> kanbans = new TreeSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Task> tasks = new TreeSet<>();

    // ---

    public void addKanban(Kanban kanban) {
        kanbans.add(kanban);
    }

    @Override
    public int compareTo(User obj) {
        return this.id.compareTo(obj.id);
    }
}
