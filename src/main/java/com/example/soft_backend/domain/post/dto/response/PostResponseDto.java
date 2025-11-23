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
    private final Long writerId;

    public static PostResponseDto from(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .commentCount(post.getComments().size())
                .boardType(post.getBoard().getType().name())
                .comments(post.getComments().stream()
                        .map(comment -> CommentResponseDto.builder()
                                .commentId(comment.getId())
                                .body(comment.getBody())
                                .build())
                        .toList())
                .writerId(post.getWriterId())   // 👈 Post 엔티티에 만든 getWriterId() 사용
                .build();
    }

//    public static PostResponseDto from(Post post) {
//        return PostResponseDto.builder()
//                .id(post.getId())
//                .title(post.getTitle())
//                .content(post.getContent())
//                .boardType(post.getBoard().getType().name())
//
////                .commentCount(post.getComments().size())
////                .comments(post.getComments().stream()
////                        .map(CommentResponseDto::from)
////                        .toList())
//
//                .WriterId(UserResponseDto.from(post.getWriter()))
//                .build();
//    }
}

