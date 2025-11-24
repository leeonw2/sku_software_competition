package com.example.soft_backend.domain.post.dto.response;

import com.example.soft_backend.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostListResponseDto {

    private Long id;
    private String title;
    private String content;
    private String boardType;
    private Long writerId;
    private String writerName;
    private int commentCount;

    public static PostListResponseDto from(Post post) {
        return PostListResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .boardType(post.getBoard().getType().name())
                .writerId(post.getWriter().getId())
                .writerName(post.getWriter().getName())
                .commentCount(post.getComments() != null ? post.getComments().size() : 0)
                .build();
    }
}
