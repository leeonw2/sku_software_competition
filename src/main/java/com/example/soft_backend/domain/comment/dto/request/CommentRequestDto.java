package com.example.soft_backend.domain.comment.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentRequestDto {

//    private final String author;
//    private final String comment;
    private final String body;
}
