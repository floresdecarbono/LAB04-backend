package com.Lab04Backend.TaskFlow.boards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Lab04Backend.TaskFlow.boards.models.Board;

import java.util.List;
import java.util.UUID;

@Repository
public interface BoardRepository extends JpaRepository<Board, UUID> {
    List<Board> findByDeletedAtIsNull();
}
