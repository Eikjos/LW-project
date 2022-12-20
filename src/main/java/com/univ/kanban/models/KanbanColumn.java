package com.univ.kanban.models;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;

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
    @Cascade({ CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    private Set<Task> tasks = new HashSet<>();

    public int compareTo(KanbanColumn col) {
        return this.order.compareTo(col.order);
    }

    public int hashCode() {
        return this.id.hashCode();
    }
}
