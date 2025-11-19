package com.example.soft_backend.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequestDto {

    private String studentId;   //학번
    private String password;     //비번
    private String name;        //이름
    private String department;  //학과
    private String gender;      //성별
}