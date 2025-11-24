package com.example.soft_backend.domain.user.controller;

import com.example.soft_backend.domain.user.dto.UserResponseDto;
import com.example.soft_backend.domain.user.entity.UserEntity;
import com.example.soft_backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    // 프로필 조회
    @GetMapping("/{userId}")
    public UserResponseDto getUserProfile(@PathVariable Long userId) {

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        return UserResponseDto.from(user);
    }
}
