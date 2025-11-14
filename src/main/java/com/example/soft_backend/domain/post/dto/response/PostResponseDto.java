package com.example.soft_backend.domain.post.dto.response;


import com.example.soft_backend.domain.comment.dto.response.CommentResponseDto;
import com.example.soft_backend.domain.post.repository.PostRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
public class PostResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final Integer commentCount;
    private final List<CommentResponseDto> comments;
}

