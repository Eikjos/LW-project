package com.univ.kanban.dto;

import com.mysql.cj.x.protobuf.MysqlxCrud.Column;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ColumnDto implements Comparable<ColumnDto> {

    @NotNull
    @NotEmpty
    private String nom;

    @NotNull
    private Integer order;

    @Override
    public int compareTo(ColumnDto col) {
        if (col == null) {
            return 1;
        }
        return order.compareTo(col.order);
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
