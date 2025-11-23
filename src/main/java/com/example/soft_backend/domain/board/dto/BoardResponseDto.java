package com.example.soft_backend.domain.board.dto;

import com.example.soft_backend.domain.board.entity.BoardEntity;
import com.example.soft_backend.domain.board.entity.BoardType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardResponseDto {

    private Long id;
    private BoardType type;
    private String name;
    private String description;

    public static BoardResponseDto from(BoardEntity board) {
        return BoardResponseDto.builder()
                .id(board.getId())
                .type(board.getType())
                .name(board.getName())
                .description(board.getDescription())
                .build();
    }
}