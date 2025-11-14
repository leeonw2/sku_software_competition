package com.example.soft_backend.domain.post.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreatePostRequestDto {
    private final String title;
    private final String content;
}



