package com.univ.kanban.dto;

import java.util.Set;
import java.util.TreeSet;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import com.univ.kanban.models.User;
import lombok.Data;

@Data
public class KanbanDto {

    public KanbanDto() {
        super();
        columns = new TreeSet<ColumnDto>();
        ColumnDto col1 = new ColumnDto("Stories", 0);
        ColumnDto col2 = new ColumnDto("Terminés", 1);
        columns.add(col1);
        columns.add(col2);
    }

    @NotNull
    @NotEmpty
    private String nom;

    private String description;

    @NotNull
    private User creator;

    private Set<User> members;

    @NotNull
    private Set<ColumnDto> columns;

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
        return columns;
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
        this.columns = columns;
    }

    public void SetMembers(Set<User> users) {
        this.members = users;
    }

}
