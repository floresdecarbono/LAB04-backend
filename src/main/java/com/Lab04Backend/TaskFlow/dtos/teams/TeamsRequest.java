package com.Lab04Backend.TaskFlow.dtos.teams;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TeamsRequest(UUID id, @NotNull(message = "Team's name cannot be null") String name) {
}
