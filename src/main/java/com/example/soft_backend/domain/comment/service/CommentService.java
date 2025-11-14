package com.example.soft_backend.domain.comment.service;

//import com.example.mentor_mentee.domain.comment.dto.request.CommentRequestDto;
//import com.example.mentor_mentee.domain.comment.dto.response.CommentResponseDto;
//import com.example.mentor_mentee.domain.comment.entity.Comment;
//import com.example.mentor_mentee.domain.comment.repository.CommentRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@RequiredArgsConstructor
//@Service
//public class CommentService {
//
//    private final CommentRepository commentRepository;
//
//    public CommentResponseDto create(CommentRequestDto commentRequestDto) {
//        // DTO -> Entity
//        Comment comment = Comment.builder()
//                .author(commentRequestDto.getAuthor())
//                .content(commentRequestDto.getComment())
//                .build();
//
//        // DB 저장
//        Comment savedComment = commentRepository.save(comment);
//
//        // Entity -> Response DTO
//        return CommentResponseDto.builder()
//                .id(savedComment.getId())
//                .author(savedComment.getAuthor())
//                .content(savedComment.getContent())
//                .views(savedComment.getViews())
//                .build();
//    }
//
//}
import com.example.soft_backend.domain.comment.dto.request.CommentRequestDto;
import com.example.soft_backend.domain.comment.dto.response.CommentResponseDto;
import com.example.soft_backend.domain.comment.entity.Comment;
import com.example.soft_backend.domain.comment.repository.CommentRepository;
import com.example.soft_backend.domain.post.entity.Post;
import com.example.soft_backend.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentResponseDto createComment(Long postId, CommentRequestDto commentRequestDto) {
        // 1. postId가 식별자인 Post를 조회
        Post post = postRepository.findById(postId).orElse(null);

        // 2. comment 생성
        Comment comment = Comment.builder()
                .body(commentRequestDto.getBody())
                .post(post)
                .build();

        // 3. comment 저장 및 반환
        Comment savedComment =  commentRepository.save(comment);

        return CommentResponseDto.builder()
                .commentId(savedComment.getId())
                .body(savedComment.getBody())
                .build();
    }

    public String deleteComment(Long postId, Long commentId) {
        // 1. postId가 식별자인 Post가 존재하는지 확인
        Post post = postRepository.findById(postId).orElse(null);
        if(post == null) {
            return postId + "번 게시글은 존재하지 않습니다.";
        }

        // 2. 조건에 따라 comment 삭제 여부 결정
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null) {
            return commentId + "번 댓글은 존재하지 않습니다.";
        }

        if (post.getComments().contains(comment)) {
            commentRepository.delete(comment);
            return commentId + "번 댓글이 삭제되었습니다.";
        } else {
            return commentId + "번 댓글은" + postId + "번 게시글의 댓글이 아닙니다.";
        }
    }
}

