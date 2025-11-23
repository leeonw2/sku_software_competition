package com.example.soft_backend.domain.board.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, length = 30)
    private BoardType type;      // RECRUIT / KNOWLEDGE / PAST_EXAM

    @Column(nullable = false, length = 50)
    private String name;         // 한글 이름 (모집 게시판 등)

    @Column(length = 255)
    private String description;  // 설명문
}