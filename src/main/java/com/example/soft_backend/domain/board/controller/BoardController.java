package com.example.soft_backend.domain.board.controller;

import com.example.soft_backend.domain.board.dto.BoardResponseDto;
import com.example.soft_backend.domain.board.entity.BoardType;
import com.example.soft_backend.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 전체 게시판 목록 조회
    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> getBoards() {
        return ResponseEntity.ok(boardService.getAllBoards());
    }

    // 특정 게시판 한 개 조회 (RECRUIT / KNOWLEDGE / PAST_EXAM)
    @GetMapping("/{type}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable BoardType type) {
        return ResponseEntity.ok(boardService.getBoard(type));
    }
}