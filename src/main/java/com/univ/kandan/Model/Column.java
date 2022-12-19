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

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Integer getOrder() {
        return order;
    }

    public Kanban getKandan() {
        return kanban;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setNom(String nom) {
        this.nom = nom;
    } 

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setKanban(Kanban kanban) {
        this.kanban = kanban;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
    }

}
