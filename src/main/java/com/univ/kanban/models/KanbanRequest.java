package com.univ.kanban.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class KanbanRequest {

    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Kanban kanban;

}
