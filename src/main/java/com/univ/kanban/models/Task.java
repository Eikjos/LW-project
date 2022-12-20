package com.univ.kanban.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Data
@Entity
public class Task implements Comparable<Task>{
    
    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long id;

    @Column(name = "nom")
    public String nom;

    @Column(name = "description")
    public String descirption;

    @Column(name = "order")
    public Integer order;

    @ManyToOne(optional = false)
    @Fetch(FetchMode.JOIN)
    private KanbanColumn column;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    private User user;

    public int compareTo(Task task) {
        return this.order.compareTo(task.order);
    }
}
