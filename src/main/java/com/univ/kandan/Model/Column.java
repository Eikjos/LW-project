package com.univ.kandan.model;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Column implements Comparable<Column>{


    public Column() {
        tasks = new TreeSet<Task>();
    }

    @Id
    @GeneratedValue
    @javax.persistence.Column(name = "Id")
    private Long id;

    @javax.persistence.Column(name = "nom")
    private String nom;

    @javax.persistence.Column(name = "order")
    private Integer order;

    @ManyToOne(optional = false)
    private Kanban kanban;

    @OneToMany(mappedBy = "colum", orphanRemoval = true)
    private Set<Task> tasks;

    public int compareTo(Column col) {
        return this.order.compareTo(col.order);
    }

}
