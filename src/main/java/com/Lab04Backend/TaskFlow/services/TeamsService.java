package com.Lab04Backend.TaskFlow.services;

import com.Lab04Backend.TaskFlow.models.Teams;
import com.Lab04Backend.TaskFlow.repositories.TeamsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TeamsService {

    private final TeamsRepository repository;

    public TeamsService(TeamsRepository repository) {
        this.repository = repository;
    }

    public List<Teams> findAll() {
        return repository.findAll();
    }

    public Teams findOne(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Team with this ID doesn't exist."));
    }

    public Teams save(Teams teams) {
        return repository.save(teams);
    }

    public Teams update(Teams teams) {
        if (repository.existsById(teams.getId())) return repository.save(teams);
        else throw new IllegalArgumentException("Team with this ID doesn't exist.");
    }

    public void delete(UUID id) {
        if (repository.existsById(id)) repository.deleteById(id);
    }

}
