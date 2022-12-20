package com.univ.kanban.services;

import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.univ.kanban.dto.ColumnDto;
import com.univ.kanban.dto.KanbanDto;
import com.univ.kanban.models.KanbanColumn;
import com.univ.kanban.models.Kanban;
import com.univ.kanban.repositories.ColumnRepository;
import com.univ.kanban.repositories.KanbanRepository;

@Service
public class KanbanService {

    private final ModelMapper modelMapper;
    private final KanbanRepository kanbanRepository;
    private final ColumnRepository columnRepository;

    public KanbanService(KanbanRepository kanbanRepository, ColumnRepository columnRepository, ModelMapper modelMapper) {
        this.kanbanRepository = kanbanRepository;
        this.columnRepository = columnRepository;
        this.modelMapper = modelMapper;
    }

    public Set<Kanban> findAllIsPublic() {
        return kanbanRepository.findAllByIsPublic(true);
    }

    public Set<Kanban> findAllByUser(Long userId) {
        return kanbanRepository.findAllByUser(userId);
    }

    public Kanban findById(Long id) {
        return kanbanRepository.findById(id);
    }

    public Kanban create(KanbanDto kanbanDto) {
        Kanban kanban = new Kanban();
        beforeCreate(kanbanDto, kanban);
        kanban = kanbanRepository.save(kanban);
        afterCreate(kanbanDto, kanban);
        return kanban;
    }

    public void beforeCreate(KanbanDto kanbanDto, Kanban kanban) {
        return;
    }

    public void afterCreate(KanbanDto kanbanDto, Kanban kanban) {
        for (ColumnDto col : kanbanDto.getColumns()) {
            KanbanColumn column = modelMapper.map(col, KanbanColumn.class);
            column.setKanban(kanban);
            columnRepository.save(column);
        }
    }

}