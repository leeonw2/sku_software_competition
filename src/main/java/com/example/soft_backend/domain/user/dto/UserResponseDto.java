package com.example.soft_backend.domain.user.dto;

import com.example.soft_backend.domain.user.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDto {

    private Long id;
    private String studentId;
    private String name;
    private String department;
    private String gender;

    public static UserResponseDto from(UserEntity user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .studentId(user.getStudentId())
                .name(user.getName())
                .build();
    }
}