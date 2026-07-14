package com.Lab04Backend.TaskFlow.dtos.board;

import com.Lab04Backend.TaskFlow.models.enums.BoardStatus;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record CreateBoardRequest(
    @NotNull(message = "Status é obrigatório")
    BoardStatus status,

    List<UUID> userIds,

    List<UUID> adminIds
) {}
