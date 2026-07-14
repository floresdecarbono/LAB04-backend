package com.Lab04Backend.TaskFlow.boards.dtos;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.Lab04Backend.TaskFlow.boards.models.Board;
import com.Lab04Backend.TaskFlow.boards.models.BoardStatus;

public record BoardResponse(
    UUID id,
    BoardStatus status,
    List<UUID> userIds,
    List<UUID> adminIds,
    List<UUID> taskIds,
    Instant createdAt,
    Instant updatedAt,
    Instant deletedAt
) {
    public static BoardResponse fromEntity(Board board) {
        return new BoardResponse(
            board.getId(),
            board.getStatus(),
            board.getUserIds() != null ? board.getUserIds() : Collections.emptyList(),
            board.getAdminIds() != null ? board.getAdminIds() : Collections.emptyList(),
            Collections.emptyList(),
            board.getCreatedAt(),
            board.getUpdatedAt(),
            board.getDeletedAt()
        );
    }
}
