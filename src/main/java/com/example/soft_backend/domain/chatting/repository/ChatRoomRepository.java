package com.example.soft_backend.domain.chatting.repository;

import com.example.soft_backend.domain.chatting.entity.ChatRoom;
import com.example.soft_backend.domain.post.entity.Post;
import com.example.soft_backend.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    // 내가 참여한 모든 채팅방
    @Query("""
            select r
            from ChatRoom r
            where r.writer = :user or r.requester = :user
            """)
    List<ChatRoom> findByUser(@Param("user") UserEntity user);

    // 같은 글, 같은 두 사람이 이미 만든 방이 있는지 체크
    Optional<ChatRoom> findByPostAndWriterAndRequester(Post post, UserEntity writer, UserEntity requester);
}
