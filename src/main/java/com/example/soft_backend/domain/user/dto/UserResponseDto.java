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

    private String coopStyleCode;   // SC
    private String coopStyleLabel;   //멘트

    public static UserResponseDto from(UserEntity user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .studentId(user.getStudentId())
                .name(user.getName())
                .department(user.getDepartment())
                .gender(user.getGender())
                .coopStyleCode(
                        user.getCoopStyle() != null ? user.getCoopStyle().name() : null
                )
                .coopStyleLabel(
                        user.getCoopStyle() != null ? user.getCoopStyle().getLabel() : null
                )
                .build();
    }
}