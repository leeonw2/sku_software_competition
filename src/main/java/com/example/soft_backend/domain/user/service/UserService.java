package com.example.soft_backend.domain.user.service;

import com.example.soft_backend.domain.user.entity.CoopStyle;
import com.example.soft_backend.domain.user.entity.UserEntity;
import com.example.soft_backend.domain.user.entity.UserRole;
import com.example.soft_backend.domain.user.repository.UserRepository;
import com.example.soft_backend.domain.user.dto.LoginRequestDto;
import com.example.soft_backend.domain.user.dto.SignupRequestDto;
import com.example.soft_backend.domain.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDto signup(SignupRequestDto request) {

        // 학번 중복 체크
        if (userRepository.existsByStudentId(request.getStudentId())) {
            throw new IllegalArgumentException("이미 존재하는 학번입니다.");
        }

        UserEntity user = UserEntity.builder()
                .studentId(request.getStudentId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .department(request.getDepartment())
                .gender(request.getGender())
                .role(UserRole.USER)
                .coopStyle(request.getCoopStyle() != null
                        ? CoopStyle.valueOf(request.getCoopStyle())
                        : null)
                .build();

        UserEntity saved = userRepository.save(user);
        return UserResponseDto.from(saved);
    }

    public UserResponseDto login(LoginRequestDto request) {

        UserEntity user = userRepository.findByStudentId(request.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("학번 또는 비밀번호가 올바르지 않습니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("학번 또는 비밀번호가 올바르지 않습니다.");
        }

        return UserResponseDto.from(user);
    }


    public UserEntity findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
    }

}
