package com.Lab04Backend.TaskFlow.activities.dtos;

import com.Lab04Backend.TaskFlow.activities.entity.Activity;

import java.time.LocalDateTime;

public record ActivityResponseDTO(

        String id,
        String userId,
        String description,
        LocalDateTime createdAt

) {

    public static ActivityResponseDTO from(Activity activity) {

        return new ActivityResponseDTO(
                activity.getId(),
                activity.getUser().getId(),
                activity.getDescription(),
                activity.getCreatedAt()
        );
    }

}
