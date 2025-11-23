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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public CommentResponseDto createComment(Long postId, CommentRequestDto commentRequestDto) {
        Post post = findPostOrThrow(postId);

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

    @Transactional
    public String deleteComment(Long postId, Long commentId) {
        Post post = findPostOrThrow(postId);

        Comment comment = findCommentOrThrow(commentId);

        if (!post.getComments().contains(comment)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    commentId + "번 댓글은 " + postId + "번 게시글의 댓글이 아닙니다.");
        }

        commentRepository.delete(comment);
        return commentId + "번 댓글이 삭제되었습니다.";
    }

    private Post findPostOrThrow(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."));
    }

    private Comment findCommentOrThrow(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다."));
    }
}

