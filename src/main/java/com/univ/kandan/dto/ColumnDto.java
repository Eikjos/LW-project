package com.univ.kandan.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ColumnDto {

    public ColumnDto(String nom, Integer order) {
        super();
        this.nom = nom;
        this.order = order;
    }

    @NotNull
    @NotEmpty
    private String nom;

    @NotNull
    private Integer order;

    public String getNom() {
        return nom;
    }

    public Integer getOrder() {
        return order;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

}
