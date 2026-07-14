package com.Lab04Backend.TaskFlow.boards.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

import com.Lab04Backend.TaskFlow.boards.models.BoardStatus;

public record CreateBoardRequest(
    @NotNull(message = "Status é obrigatório")
    BoardStatus status,

    List<UUID> userIds,

    List<UUID> adminIds
) {}
