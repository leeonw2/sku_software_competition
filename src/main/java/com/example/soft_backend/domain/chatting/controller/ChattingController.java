package com.example.soft_backend.domain.chatting.controller;

import com.example.soft_backend.domain.chatting.dto.request.CreateChatRoomRequestDto;
import com.example.soft_backend.domain.chatting.dto.request.SendMessageRequestDto;
import com.example.soft_backend.domain.chatting.dto.response.ChatMessageResponseDto;
import com.example.soft_backend.domain.chatting.dto.response.ChatRoomResponseDto;
import com.example.soft_backend.domain.chatting.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChattingController {

    private final ChatService chatService;

    /**
     * 채팅방 생성 (팀플 모집글 → 채팅 신청 눌렀을 때)
     * 예: POST /api/chat/rooms?meId=1
     * body: { "postId": 10 }
     */
    @PostMapping("/rooms")
    public ChatRoomResponseDto createChatRoom(
            @RequestParam("meId") Long meId,
            @RequestBody CreateChatRoomRequestDto dto
    ) {
        return chatService.createChatRoom(meId, dto);
    }

    /**
     * 내가 참여한 모든 채팅방 리스트 조회
     * 예: GET /api/chat/rooms?meId=1
     */
    @GetMapping("/rooms")
    public List<ChatRoomResponseDto> getMyChatRooms(
            @RequestParam("meId") Long meId
    ) {
        return chatService.getMyChatRooms(meId);
    }

    /**
     * 특정 채팅방의 메시지 목록 조회
     * 예: GET /api/chat/rooms/{roomId}/messages?meId=1
     */
    @GetMapping("/rooms/{roomId}/messages")
    public List<ChatMessageResponseDto> getChatMessages(
            @PathVariable Long roomId,
            @RequestParam("meId") Long meId
    ) {
        return chatService.getMessages(roomId, meId);
    }

    /**
     * 특정 채팅방에 메시지 보내기
     * 예: POST /api/chat/rooms/{roomId}/messages?meId=1
     * body: { "content": "안녕하세요!" }
     */
    @PostMapping("/rooms/{roomId}/messages")
    public ChatMessageResponseDto sendMessage(
            @PathVariable Long roomId,
            @RequestParam("meId") Long meId,
            @RequestBody SendMessageRequestDto dto
    ) {
        return chatService.sendMessage(roomId, meId, dto);
    }
}
