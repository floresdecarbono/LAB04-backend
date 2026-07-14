package com.Lab04Backend.TaskFlow.activities.services;

import com.Lab04Backend.TaskFlow.activities.dtos.ActivityResponseDTO;
import com.Lab04Backend.TaskFlow.activities.entity.Activity;
import com.Lab04Backend.TaskFlow.activities.repositories.ActivityRepository;
import com.Lab04Backend.TaskFlow.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository repository;

    public void register(User user, String description) {

        Activity activity = Activity.builder()
                .user(user)
                .description(description)
                .build();

        repository.save(activity);
    }

    public List<ActivityResponseDTO> findLastActivities(User user) {

        return repository.findTop7ByUserOrderByCreatedAtDesc(user)
                .stream()
                .map(ActivityResponseDTO::from)
                .toList();
    }

}