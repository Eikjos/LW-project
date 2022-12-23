package com.univ.kanban.dto;

import lombok.Data;

@Data
public class KanbanRequestDto {
    
    public KanbanRequestDto(Long kanbanId) {
        this.kanban =  kanbanId;
    }

    private Long kanban;

    private Long user;

}
