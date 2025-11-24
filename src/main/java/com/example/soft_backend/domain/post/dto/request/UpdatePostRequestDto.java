package com.example.soft_backend.domain.post.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdatePostRequestDto {
    private String title;
    private String content;
}
