package com.example.soft_backend.domain.post.dto.response;

import lombok.Getter;
import com.example.soft_backend.domain.comment.dto.response.CommentResponseDto;
import com.example.soft_backend.domain.post.repository.PostRepository;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
public class PostListResponseDto {
    private final Long id;
    private final String title;
    private final Integer commentCount;
    private Long writerId;
    private String writerName;

}