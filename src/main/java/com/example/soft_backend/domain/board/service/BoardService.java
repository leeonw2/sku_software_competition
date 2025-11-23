package com.example.soft_backend.domain.board.service;

import com.example.soft_backend.domain.board.dto.BoardResponseDto;
import com.example.soft_backend.domain.board.entity.BoardEntity;
import com.example.soft_backend.domain.board.entity.BoardEntity;
import com.example.soft_backend.domain.board.entity.BoardType;
import com.example.soft_backend.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    // 전체 게시판 목록
    public List<BoardResponseDto> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(BoardResponseDto::from)
                .toList();
    }

    // 타입으로 특정 게시판 조회
    public BoardResponseDto getBoard(BoardType type) {
        BoardEntity board = boardRepository.findByType(type)
                .orElseThrow(() -> new IllegalArgumentException("해당 타입의 게시판이 없습니다: " + type));
        return BoardResponseDto.from(board);
    }
}