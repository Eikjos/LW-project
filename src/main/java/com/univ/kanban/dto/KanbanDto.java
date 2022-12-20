package com.univ.kanban.dto;

import java.util.Set;
import java.util.TreeSet;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import com.univ.kanban.models.User;
import lombok.Data;

@Data
public class KanbanDto {

    public KanbanDto(User user) {
        colums = new TreeSet<ColumnDto>();
        colums.add(new ColumnDto("Stories", 0));
        colums.add(new ColumnDto("Terminés", 1));
        members = new TreeSet<User>();
        members.add(user);
        creator = user;
    }

    @NotNull
    @NotEmpty
    private String nom;

    private String description;

    @NotNull
    private User creator;

    private Set<User> members;

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

    public Set<User> getMembers() {
        return members;
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

    public void SetMembers(Set<User> users) {
        this.members = users;
    }

}
