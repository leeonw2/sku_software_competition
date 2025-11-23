package com.example.soft_backend.domain.chatting.dto.request;

import lombok.Getter;

@Getter
public class CreateChatRoomRequestDto {
    // 채팅 신청을 누른 게시글 id
    private Long postId;
}