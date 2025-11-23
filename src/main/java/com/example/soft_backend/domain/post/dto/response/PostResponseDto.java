package com.example.soft_backend.domain.post.dto.response;


import com.example.soft_backend.domain.comment.dto.response.CommentResponseDto;
import com.example.soft_backend.domain.post.repository.PostRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import com.example.soft_backend.domain.post.entity.Post;
import com.example.soft_backend.domain.user.dto.UserResponseDto;

import java.util.List;

@Getter
@Builder
public class PostResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final Integer commentCount;
    private final String boardType; // 이 부분 ???
    private final List<CommentResponseDto> comments;

    private UserResponseDto writer; //작성자 정보

    public static PostResponseDto from(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .boardType(post.getBoard().getType().name())

//                .commentCount(post.getComments().size())
//                .comments(post.getComments().stream()
//                        .map(CommentResponseDto::from)
//                        .toList())

                .writer(UserResponseDto.from(post.getWriter()))
                .build();
    }
}

