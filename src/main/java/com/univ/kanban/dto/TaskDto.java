package com.univ.kanban.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TaskDto {

    @NotNull
    @NotEmpty
    private String title;

    private String description;

    private Long user_id;

    @NotNull
    private Long column_id;

}
