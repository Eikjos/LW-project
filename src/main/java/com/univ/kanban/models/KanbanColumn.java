package com.univ.kanban.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class KanbanColumn implements Comparable<KanbanColumn>{

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "order")
    private Integer order;

    @ManyToOne(optional = false)
    private Kanban kanban;

    @OneToMany(mappedBy = "column", orphanRemoval = true)
    private Set<Task> tasks = new HashSet<>();

    public int compareTo(KanbanColumn col) {
        return this.order.compareTo(col.order);
    }
}
