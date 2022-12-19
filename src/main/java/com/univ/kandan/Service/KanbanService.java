package com.univ.kandan.service;

import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.univ.kandan.dto.ColumnDto;
import com.univ.kandan.dto.KanbanDto;
import com.univ.kandan.model.Column;
import com.univ.kandan.model.Kanban;
import com.univ.kandan.repository.ColumnRepository;
import com.univ.kandan.repository.KanbanRepository;

@Service
public class KanbanService {

    private final ModelMapper modelMapper;
    private final KanbanRepository kanbanRepostitory;
    private final ColumnRepository columnRepository;

    public KanbanService(KanbanRepository kanbanRepository, ColumnRepository columnRepository, ModelMapper modelMapper) {
        this.kanbanRepostitory = kanbanRepository;
        this.columnRepository = columnRepository;
        this.modelMapper = modelMapper;
    }

    public Set<Kanban> findAllIsPublic() {
        return kanbanRepostitory.findAllByIsPublic(true);
    }

    public Set<Kanban> findAllByUser(Long userId) {
        return kanbanRepostitory.findAllByUser(userId);
    }

    public Kanban findById(Long id) {
        return kanbanRepostitory.findById(id);
    }

    public Kanban create(KanbanDto kanbanDto) {
        Kanban kanban = new Kanban();
        beforeCreate(kanbanDto, kanban);
        kanban = kanbanRepostitory.save(kanban);
        afterCreate(kanbanDto, kanban);
        return kanban;
    }

    public void beforeCreate(KanbanDto kanbanDto, Kanban kanban) {
        return;
    }

    public void afterCreate(KanbanDto kanbanDto, Kanban kanban) {
        for (ColumnDto col : kanbanDto.getColumns()) {
            Column column = modelMapper.map(col, Column.class);
            column.setKanban(kanban);
            columnRepository.save(column);
        }
    }

}
