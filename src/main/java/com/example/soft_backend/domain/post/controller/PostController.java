//package com.example.soft_backend.domain.post.controller;
//import com.example.soft_backend.domain.post.dto.request.CreatePostRequestDto;
//import com.example.soft_backend.domain.post.dto.request.UpdatePostRequestDto;
//import com.example.soft_backend.domain.post.dto.response.PostListResponseDto;
//import com.example.soft_backend.domain.post.dto.response.PostResponseDto;
//import com.example.soft_backend.domain.post.service.PostService;
//import java.util.List;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RequiredArgsConstructor
//@RestController  //해당 클래스가 Web 요청을 담당하는 컨트롤러 기능을 수행
//@RequestMapping("/api/posts")  //요청 기본 엔드포인트(url)를 지정
//@CrossOrigin(origins = "http://localhost:3000")
//public class PostController {
//    private final PostService postService;
//
//    @PostMapping
//    public PostResponseDto createPost(@RequestBody CreatePostRequestDto createPostRequestDto) {
//        PostResponseDto responseDto = postService.createPost(createPostRequestDto);
//        return responseDto;
//    }
//
//    @GetMapping
//    public List<PostListResponseDto> getAllPosts() {
//        List<PostListResponseDto> responseDtos = postService.readPostList();
//        return responseDtos;
//    }
//
//    @GetMapping("/{post-id}") //비공개 게시글이라면 사용자의 아이디와 같이 일치한지 아닌지 처리를 해서 사용자의 아이디를 받는 경우도 있다.
//    public PostResponseDto getPostById(@PathVariable(value = "post-id") Long id){
//        PostResponseDto responseDto = postService.readPost(id);  //crud에 따라 read를 쓴거
//        return responseDto;
//    }
//
//    @PutMapping("/{post-id}")
//    public PostResponseDto updatePost(@RequestBody UpdatePostRequestDto updatePostRequestDto ,@PathVariable(value = "post-id") Long id){
//        PostResponseDto responseDto = postService.updatePost(updatePostRequestDto, id);
//        return responseDto;
//    }
//
//    @DeleteMapping("/{post-id}")
//    public String deletePost(@PathVariable(value = "post-id") Long id){
//        String responseDto = postService.deletePost(id);
//        return responseDto;
//    }
//}
package com.example.soft_backend.domain.post.controller;

import com.example.soft_backend.domain.post.dto.request.CreatePostRequestDto;
import com.example.soft_backend.domain.post.dto.request.UpdatePostRequestDto;
import com.example.soft_backend.domain.post.dto.response.PostListResponseDto;
import com.example.soft_backend.domain.post.dto.response.PostResponseDto;
import com.example.soft_backend.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 생성
    @PostMapping
    public PostResponseDto createPost(@RequestBody CreatePostRequestDto dto) {
        return postService.createPost(dto);
    }

    // 게시글 목록 (boardType = RECRUIT / KNOWLEDGE / EXAM)
    @GetMapping
    public List<PostListResponseDto> getPostList(
            @RequestParam(value = "boardType", required = false) String boardType
    ) {
        return postService.getPostList(boardType);
    }

    // 게시글 상세
    @GetMapping("/{postId}")
    public PostResponseDto getPostDetail(@PathVariable Long postId) {
        return postService.getPostDetail(postId);
    }

    // 게시글 수정
    @PutMapping("/{postId}")
    public PostResponseDto updatePost(
            @PathVariable Long postId,
            @RequestBody UpdatePostRequestDto dto
    ) {
        return postService.updatePost(postId, dto);
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }
}
