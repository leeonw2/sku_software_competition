//package com.example.soft_backend.domain.post.service;
//
//import com.example.soft_backend.domain.board.entity.BoardEntity;
//import com.example.soft_backend.domain.board.repository.BoardRepository;
//import com.example.soft_backend.domain.comment.dto.response.CommentResponseDto;
//import com.example.soft_backend.domain.post.dto.request.CreatePostRequestDto;
//import com.example.soft_backend.domain.post.dto.request.UpdatePostRequestDto;
//import com.example.soft_backend.domain.post.dto.response.PostListResponseDto;
//import com.example.soft_backend.domain.post.dto.response.PostResponseDto;
//import com.example.soft_backend.domain.post.entity.Post;
//import com.example.soft_backend.domain.post.repository.PostRepository;
//import com.example.soft_backend.domain.user.entity.UserEntity;
//import com.example.soft_backend.domain.user.service.UserService;
//import java.util.List;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.server.ResponseStatusException;
//
//@RequiredArgsConstructor
//@Service
//public class PostService {
//
//    private final PostRepository postRepository;
//    private final BoardRepository boardRepository;
//    private final UserService userService;
//
//    // 게시글 생성
////    @Transactional
////    public PostResponseDto createPost(CreatePostRequestDto createPostRequestDto) {
////
////        // 0. writerId null 체크 (여기서 400 던지기)
////        Long writerId = createPostRequestDto.getWriterId();
////        if (writerId == null) {
////            throw new ResponseStatusException(
////                    HttpStatus.BAD_REQUEST,
////                    "writerId는 null일 수 없습니다."
////            );
////        }
////
////        // 1. 게시판 찾기
////        BoardEntity board = boardRepository.findByType(createPostRequestDto.getBoardType())
////                .orElseThrow(() -> new ResponseStatusException(
////                        HttpStatus.NOT_FOUND,
////                        "없는 게시판 타입입니다: " + createPostRequestDto.getBoardType()
////                ));
////
////        // 2. 작성자 찾기
////        UserEntity writer = userService.findById(writerId);
////
////        // 3. Post 엔티티 생성
////        Post post = Post.builder()
////                .board(board)
////                .title(createPostRequestDto.getTitle())
////                .content(createPostRequestDto.getContent())
////                .writer(writer)
////                .build();
////
////        // 4. 저장
////        Post savedPost = postRepository.save(post);
////
////        // 5. 응답 DTO로 변환
////        return PostResponseDto.from(savedPost);
////    }
//    public PostResponseDto createPost(PostRequestDto dto) {
//
//        UserEntity writer = userRepository.findById(dto.getWriterId())
//                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
//
//        BoardEntity board = boardRepository.findByType(BoardType.valueOf(dto.getBoardType()))
//                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시판 타입입니다."));
//
//        Post post = Post.builder()
//                .title(dto.getTitle())
//                .content(dto.getContent())
//                .writer(writer) // 🟢 UserEntity로 저장해야 함
//                .board(board)   // 🟢 BoardEntity 설정
//                .build();
//
//        postRepository.save(post);
//
//        return PostResponseDto.from(post);
//    }
//
//    // 게시글 단건 조회
//    @Transactional(readOnly = true)
//    public PostResponseDto readPost(Long postId){
//        Post post = findPostOrThrow(postId);
//        return PostResponseDto.from(post);
//    }
//
//    // 게시글 수정
//    @Transactional
//    public PostResponseDto updatePost(UpdatePostRequestDto updatePostRequestDto, Long postId) {
//        Post post = findPostOrThrow(postId);
//
//        post.update(updatePostRequestDto.getTitle(), updatePostRequestDto.getContent());
//
//        // 영속 상태라 따로 save 필요 X
//        return PostResponseDto.from(post);
//    }
//
//    // 게시글 삭제
//    @Transactional
//    public String deletePost(Long postId) {
//        Post post = findPostOrThrow(postId);
//
//        postRepository.delete(post);
//        return postId + "번 게시글 삭제 완료";
//    }
//
//    // 게시글 리스트 조회
//    @Transactional(readOnly = true)
//    public List<PostListResponseDto> readPostList(){
//        return postRepository.findAll().stream()
//                .map(post -> PostListResponseDto.builder()
//                        .id(post.getId())
//                        .title(post.getTitle())
//                        .commentCount(post.getComments().size())
//                        .build()
//                ).toList();
//    }
//
//    // 공통: id로 Post 찾기
//    private Post findPostOrThrow(Long postId) {
//        return postRepository.findById(postId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."));
//    }
//}
package com.example.soft_backend.domain.post.service;

import com.example.soft_backend.domain.board.entity.BoardEntity;
import com.example.soft_backend.domain.board.entity.BoardType;
import com.example.soft_backend.domain.board.repository.BoardRepository;
import com.example.soft_backend.domain.post.dto.request.CreatePostRequestDto;
import com.example.soft_backend.domain.post.dto.request.UpdatePostRequestDto;
import com.example.soft_backend.domain.post.dto.response.PostListResponseDto;
import com.example.soft_backend.domain.post.dto.response.PostResponseDto;
import com.example.soft_backend.domain.post.entity.Post;
import com.example.soft_backend.domain.post.repository.PostRepository;
import com.example.soft_backend.domain.user.entity.UserEntity;
import com.example.soft_backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    // 생성
    @Transactional
    public PostResponseDto createPost(CreatePostRequestDto dto) {

        UserEntity writer = userRepository.findById(dto.getWriterId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        BoardType boardType = BoardType.valueOf(dto.getBoardType());
        BoardEntity board = boardRepository.findByType(boardType)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시판 타입입니다."));

        Post post = Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(writer)
                .board(board)
                .build();

        postRepository.save(post);

        return PostResponseDto.from(post);
    }

    // 목록
    @Transactional(readOnly = true)
    public List<PostListResponseDto> getPostList(String boardType) {
        List<Post> posts;

        if (boardType == null || boardType.isBlank()) {
            posts = postRepository.findAll();
        } else {
            BoardType type = BoardType.valueOf(boardType);
            posts = postRepository.findByBoard_Type(type);
        }

        return posts.stream()
                .map(PostListResponseDto::from)
                .collect(Collectors.toList());
    }

    // 상세
    @Transactional(readOnly = true)
    public PostResponseDto getPostDetail(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        return PostResponseDto.from(post);
    }

    // 수정 (작성자 검증 X : 공모전용이면 생략해도 됨)
    @Transactional
    public PostResponseDto updatePost(Long postId, UpdatePostRequestDto dto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        post.update(dto.getTitle(), dto.getContent());
        return PostResponseDto.from(post);
    }

    // 삭제
    @Transactional
    public void deletePost(Long postId) {
        if (!postRepository.existsById(postId)) {
            throw new IllegalArgumentException("삭제할 게시글이 존재하지 않습니다.");
        }
        postRepository.deleteById(postId);
    }
}
