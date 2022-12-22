package com.univ.kanban.dto;

import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.univ.kanban.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class KanbanDto {

    public KanbanDto() {
        super();
        columns = new ArrayList<ColumnDto>();
        members = new TreeSet<User>(); 
    }

    @NotNull
    @NotEmpty
    @JsonProperty
    private String nom;

    @JsonProperty
    private String description;

    @NotNull
    private User creator;

    private Set<User> members;

    @NotNull
    @JsonProperty
    private List<ColumnDto> columns;

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

    public List<ColumnDto> getColumns() {
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

    public void setColumns(ArrayList<ColumnDto> columns) {
        this.columns = columns;
    }

    public void setMembers(Set<User> users) {
        this.members = users;
    }
}
