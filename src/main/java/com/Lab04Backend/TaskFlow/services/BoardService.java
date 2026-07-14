package com.Lab04Backend.TaskFlow.services;

import com.Lab04Backend.TaskFlow.config.ResourceNotFoundException;
import com.Lab04Backend.TaskFlow.dtos.board.BoardResponse;
import com.Lab04Backend.TaskFlow.dtos.board.CreateBoardRequest;
import com.Lab04Backend.TaskFlow.models.Board;
import com.Lab04Backend.TaskFlow.repositories.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public BoardResponse createBoard(CreateBoardRequest request) {
        Board board = Board.builder()
            .status(request.status())
            .build();

        Board savedBoard = boardRepository.save(board);
        return BoardResponse.fromEntity(savedBoard);
    }

    @Transactional(readOnly = true)
    public BoardResponse getBoardById(UUID id) {
        Board board = boardRepository.findById(id)
            .filter(b -> b.getDeletedAt() == null)
            .orElseThrow(() -> new ResourceNotFoundException("Quadro com ID " + id + " não encontrado."));
        return BoardResponse.fromEntity(board);
    }

    @Transactional(readOnly = true)
    public List<BoardResponse> listAllActiveBoards() {
        return boardRepository.findByDeletedAtIsNull().stream()
            .map(BoardResponse::fromEntity)
            .toList();
    }

    @Transactional
    public void deleteBoard(UUID id) {
        Board board = boardRepository.findById(id)
            .filter(b -> b.getDeletedAt() == null)
            .orElseThrow(() -> new ResourceNotFoundException("Quadro com ID " + id + " não encontrado."));

        board.setDeletedAt(Instant.now());
        boardRepository.save(board);
    }
}
