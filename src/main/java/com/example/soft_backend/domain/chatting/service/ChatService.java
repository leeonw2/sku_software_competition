package com.example.soft_backend.domain.chatting.service;

import com.example.soft_backend.domain.chatting.dto.request.CreateChatRoomRequestDto;
import com.example.soft_backend.domain.chatting.dto.request.SendMessageRequestDto;
import com.example.soft_backend.domain.chatting.dto.response.ChatMessageResponseDto;
import com.example.soft_backend.domain.chatting.dto.response.ChatRoomResponseDto;
import com.example.soft_backend.domain.chatting.entity.ChatMessage;
import com.example.soft_backend.domain.chatting.entity.ChatRoom;
import com.example.soft_backend.domain.chatting.repository.ChatMessageRepository;
import com.example.soft_backend.domain.chatting.repository.ChatRoomRepository;
import com.example.soft_backend.domain.post.entity.Post;
import com.example.soft_backend.domain.post.repository.PostRepository;
import com.example.soft_backend.domain.user.entity.UserEntity;
import com.example.soft_backend.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    /**
     * 채팅방 생성: postId + meId 로 방 만들기
     * - 이미 같은 글/같은 사람 조합 방 있으면 그대로 반환
     */
    public ChatRoomResponseDto createChatRoom(Long meId, CreateChatRoomRequestDto dto) {
        UserEntity me = userRepository.findById(meId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        UserEntity writer = post.getWriter();           // 글 작성자
        UserEntity requester = me;                     // 채팅 신청자

        if (writer.getId().equals(meId)) {
            throw new IllegalStateException("본인 글에는 채팅을 신청할 수 없습니다.");
        }

        // 같은 글 + 같은 두 사람 조합의 채팅방이 이미 있다면 그 방 재사용
        ChatRoom room = chatRoomRepository
                .findByPostAndWriterAndRequester(post, writer, requester)
                .orElseGet(() -> {
                    ChatRoom newRoom = ChatRoom.builder()
                            .post(post)
                            .writer(writer)
                            .requester(requester)
                            .build();
                    return chatRoomRepository.save(newRoom);
                });

        return ChatRoomResponseDto.of(room,me);
    }

    /**
     * 내가 참여한 모든 채팅방 리스트
     */
    public List<ChatRoomResponseDto> getMyChatRooms(Long meId) {
        UserEntity me = userRepository.findById(meId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        List<ChatRoom> rooms = chatRoomRepository.findByUser(me);

        return rooms.stream()
                .map(room -> ChatRoomResponseDto.of(room,me))
                .toList();
    }

    /**
     * 특정 채팅방의 메시지 목록
     */
    public List<ChatMessageResponseDto> getMessages(Long roomId, Long meId) {
        UserEntity me = userRepository.findById(meId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        ChatRoom room = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("채팅방을 찾을 수 없습니다."));

        // 이 방의 참여자인지 확인
        if (!(room.getWriter().getId().equals(meId) || room.getRequester().getId().equals(meId))) {
            throw new IllegalStateException("이 채팅방의 참가자가 아닙니다.");
        }

        List<ChatMessage> messages = chatMessageRepository.findByChatRoomOrderByCreatedAtAsc(room);

        return messages.stream()
                .map(ChatMessageResponseDto::from)
                .toList();
    }

    /**
     * 메시지 전송
     */
    public ChatMessageResponseDto sendMessage(Long roomId, Long meId, SendMessageRequestDto dto) {
        UserEntity sender = userRepository.findById(meId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        ChatRoom room = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("채팅방을 찾을 수 없습니다."));

        // (옵션) 이 방의 참가자인지 체크
        if (!(room.getWriter().getId().equals(meId) || room.getRequester().getId().equals(meId))) {
            throw new IllegalStateException("이 채팅방의 참가자가 아닙니다.");
        }

        ChatMessage message = ChatMessage.builder()
                .chatRoom(room)
                .sender(sender)
                .content(dto.getContent())
                .build();

        ChatMessage saved = chatMessageRepository.save(message);
        return ChatMessageResponseDto.from(saved);
    }
}