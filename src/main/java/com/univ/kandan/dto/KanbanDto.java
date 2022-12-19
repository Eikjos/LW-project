package com.univ.kandan.dto;

import java.util.Set;
import java.util.TreeSet;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.univ.kandan.model.User;


public class KanbanDto {


    public KanbanDto(User user) {
        colums = new TreeSet<ColumnDto>();
        colums.add(new ColumnDto("Stories", 0));
        colums.add(new ColumnDto("Terminés", 1));
        creator = user;
    }
    
    @NotNull
    @NotEmpty
    private String nom;

    private String description;

    @NotNull
    private User creator;

    @NotNull
    private Set<ColumnDto> colums;

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public User getCreator() {
        return creator;
    }

    public Set<ColumnDto> getColumns() {
        return colums;
    }

    public void setNom(String nom) {
        this.nom = nom;
    } 

    public void setDescription(String description) {
        this.description = description;
    } 

    public void setCreator(User user) {
        this.creator = user;
    } 

    public void setColumns(Set<ColumnDto> columns) {
        this.colums = columns;
    } 

}
