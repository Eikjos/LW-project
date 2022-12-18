package com.univ.kandan.Service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.univ.kandan.Repository.KanbanRepository;
import com.univ.kandan.Model.Kanban;


@Service
public class KanbanService {
    
    private final KanbanRepository kanbanRepostitory;

    @Autowired
    public KanbanService(KanbanRepository kanbanRepository) {
        this.kanbanRepostitory = kanbanRepository;
    }

    public Set<Kanban> findAllIsPublic(boolean isPublic) {
        return kanbanRepostitory.findAllByIsPublic(isPublic);
    }

}
