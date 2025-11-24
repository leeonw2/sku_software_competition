package com.example.soft_backend.domain.post.dto.response;

import com.example.soft_backend.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private String boardType;
    private Long writerId;
    private String writerName;

    public static PostResponseDto from(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .boardType(post.getBoard().getType().name())
                .writerId(post.getWriter().getId())
                .writerName(post.getWriter().getName())
                .build();
    }
}
