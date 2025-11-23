package com.example.soft_backend.domain.chatting.dto.response;

//import com.example.soft_backend.domain.chatting.entity.ChatRoom;
//import com.example.soft_backend.domain.user.dto.UserResponseDto;
//import com.example.soft_backend.domain.user.entity.UserEntity;
//import lombok.Builder;
//import lombok.Getter;
//
//import java.time.LocalDateTime;
//
//@Getter
//@Builder
//public class ChatRoomResponseDto {
//
//    private Long roomId;
//    private Long postId;
//    private String postTitle;
//
//    private Long opponentId;
//    private String opponentName;
//    private UserResponseDto me;       // 현재 로그인 사용자 정보
//
//    private LocalDateTime createdAt;
//
//    public static ChatRoomResponseDto of(ChatRoom room, UserEntity me) {
//        // 상대방(나 아닌 사람) 찾기
//        UserEntity opponent =
//                room.getWriter().getId().equals(me.getId())
//                        ? room.getRequester()
//                        : room.getWriter();
//
//        return ChatRoomResponseDto.builder()
//                .roomId(room.getId())
//                .postId(room.getPost().getId())
//                .postTitle(room.getPost().getTitle())
//                .opponentId(opponent.getId())
//                .opponentName(opponent.getName())
//                .createdAt(room.getCreatedAt())
//                .build();
//    }
//
//
//    // ⭐ fromWithMe 메서드 추가
//    public static ChatRoomResponseDto fromWithMe(UserEntity me, ChatRoom room) {
//
//        // 상대방 식별
//        UserEntity opponent = room.getWriter().getId().equals(me.getId())
//                ? room.getRequester()
//                : room.getWriter();
//
//        return ChatRoomResponseDto.builder()
//                .roomId(room.getId())
//                .postId(room.getPost().getId())
//                .me(UserResponseDto.from(me))
//                .opponent(UserResponseDto.from(opponent))
//                .createdAt(room.getCreatedAt().toString())
//                .build();
//    }
//}

import com.example.soft_backend.domain.chatting.entity.ChatRoom;
import com.example.soft_backend.domain.user.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ChatRoomResponseDto {

    private Long roomId;
    private Long postId;
    private String postTitle;

    private Long opponentId;
    private String opponentName;

    private LocalDateTime createdAt;

    // 현재 로그인 유저(me)를 기준으로 상대방 정보 세팅
    public static ChatRoomResponseDto of(ChatRoom room, UserEntity me) {

        // 상대방(나 아닌 사람) 찾기
        UserEntity opponent =
                room.getWriter().getId().equals(me.getId())
                        ? room.getRequester()
                        : room.getWriter();

        return ChatRoomResponseDto.builder()
                .roomId(room.getId())
                .postId(room.getPost().getId())
                .postTitle(room.getPost().getTitle())
                .opponentId(opponent.getId())
                .opponentName(opponent.getName())
                .createdAt(room.getCreatedAt())
                .build();
    }
}