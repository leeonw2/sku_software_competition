package com.example.soft_backend.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "users")
public class UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String studentId;  // 🔥 이메일 → 학번

    @Column(nullable = false, length = 100)
    private String password;   // 암호화된 비밀번호

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 20)
    private String department;

    @Column(nullable = false, length = 20)
    private String gender;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private CoopStyle coopStyle;   // 협업 스타일 코드

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserRole role;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.role == null) {
            this.role = UserRole.USER;
        }
    }
}
