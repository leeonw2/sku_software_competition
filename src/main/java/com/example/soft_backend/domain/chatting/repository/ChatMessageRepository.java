package com.example.soft_backend.domain.chatting.repository;

import com.example.soft_backend.domain.chatting.entity.ChatMessage;
import com.example.soft_backend.domain.chatting.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findByChatRoomOrderByCreatedAtAsc(ChatRoom chatRoom);
}