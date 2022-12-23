package com.univ.kanban.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.univ.kanban.models.KanbanRequest;
import com.univ.kanban.models.User;
import com.univ.kanban.repositories.KanbanRequestRepository;

@Service
public class KanbanRequestService {

    private final KanbanRequestRepository kanbanRequestRepository;

    public KanbanRequestService(KanbanRequestRepository kanbanRequestRepository) {
        this.kanbanRequestRepository = kanbanRequestRepository;
    }
    
    public Set<KanbanRequest> getByUser(User user) {
        return kanbanRequestRepository.findAllByUser(user);
    }

    public KanbanRequest save(KanbanRequest request) {
        return kanbanRequestRepository.save(request);
    }
}
