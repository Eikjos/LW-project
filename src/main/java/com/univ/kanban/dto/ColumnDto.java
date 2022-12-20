package com.univ.kanban.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ColumnDto {

    @NotNull
    @NotEmpty
    private String nom;

    @NotNull
    private Integer order;
}
