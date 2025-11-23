package com.example.soft_backend.domain.post.dto.request;

import com.example.soft_backend.domain.board.entity.BoardEntity;
import com.example.soft_backend.domain.board.entity.BoardType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreatePostRequestDto {
    private final Long writerId;
    private final BoardType boardType;  //게시판 종류
    private final String title;
    private final String content;
}



