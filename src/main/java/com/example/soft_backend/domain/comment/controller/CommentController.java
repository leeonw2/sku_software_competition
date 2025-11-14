package com.example.soft_backend.domain.comment.controller;

import com.example.soft_backend.domain.comment.dto.request.CommentRequestDto;
import com.example.soft_backend.domain.comment.dto.response.CommentResponseDto;
import com.example.soft_backend.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService ;

    @PostMapping("/{post-id}")
    public CommentResponseDto createComment(@PathVariable(value = "post-id") Long postId, @RequestBody CommentRequestDto commentRequestDto) {
        CommentResponseDto responseDto = commentService.createComment(postId, commentRequestDto);
        return responseDto;
    }

    @DeleteMapping("/{post-id}/{comment-id}")
    public String deleteComment(@PathVariable(value = "post-id") Long postId, @PathVariable(value = "comment-id") Long commentId){
        return commentService.deleteComment(postId, commentId);
    }
}