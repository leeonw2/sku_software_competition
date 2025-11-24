//package com.example.soft_backend.domain.post.dto.request;
//
//import com.example.soft_backend.domain.board.entity.BoardEntity;
//import com.example.soft_backend.domain.board.entity.BoardType;
//import lombok.Builder;
//import lombok.Getter;
//
//@Getter
//@Builder
//public class CreatePostRequestDto {
//    private final Long writerId;
//    private final BoardType boardType;  //게시판 종류
//    private final String title;
//    private final String content;
//}

package com.example.soft_backend.domain.post.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreatePostRequestDto {
    private String boardType;   // "RECRUIT" / "KNOWLEDGE" / "EXAM"
    private String title;
    private String content;
    private Long writerId;      // 프론트에서 로그인한 사용자의 id를 넣어줌
}

