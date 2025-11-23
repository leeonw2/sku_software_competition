package com.example.soft_backend.domain.post.service;





import com.example.soft_backend.domain.board.entity.BoardEntity;
import com.example.soft_backend.domain.board.repository.BoardRepository;
import com.example.soft_backend.domain.comment.dto.response.CommentResponseDto;
import com.example.soft_backend.domain.comment.entity.Comment;
import com.example.soft_backend.domain.post.dto.request.CreatePostRequestDto;
import com.example.soft_backend.domain.post.dto.request.UpdatePostRequestDto;
import com.example.soft_backend.domain.post.dto.response.PostListResponseDto;
import com.example.soft_backend.domain.post.dto.response.PostResponseDto;
import com.example.soft_backend.domain.post.entity.Post;
import com.example.soft_backend.domain.post.repository.PostRepository;
import com.example.soft_backend.domain.user.entity.UserEntity;
import com.example.soft_backend.domain.user.service.UserService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final UserService userService;

    @Transactional
    public PostResponseDto createPost(CreatePostRequestDto createPostRequestDto) {

        BoardEntity board = boardRepository.findByType(createPostRequestDto.getBoardType())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "없는 게시판 타입입니다: " + createPostRequestDto.getBoardType()
                ));


        //현재 로그인한 유저 불러오는 메소드 Userservice에서 만들거임
//        UserEntity writer = userService.getCurrentUser();
        UserEntity writer = userService.findById(createPostRequestDto.getWriterId());
        // 1. PostRequestDto에 있는 값으로 post 클래스 객체 생성
        Post post = Post.builder()
                .board(board)
                .title(createPostRequestDto.getTitle())
                .content(createPostRequestDto.getContent())
                .writer(writer)
                .build();

        // 2. 새로 생성한 post 객체 DB에 저장
        Post savedPost = postRepository.save(post);

        // 3. 새로 생성한 post 객체 데이터에서 필요한 부분을 PostResponseDto에 넣어서 PostResponseDto 객체 생성
        return PostResponseDto.builder()
                .id(savedPost.getId())
                .title(savedPost.getTitle())
                .content(savedPost.getContent())
                .build();
//        return PostResponseDto.from(post);    //이게 맞냐?????
    }

    @Transactional(readOnly = true)
    public PostResponseDto readPost(Long postId){
        Post post = findPostOrThrow(postId);

        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .commentCount(post.getComments().size())
                .comments(post.getComments().stream()
                        .map(comment -> CommentResponseDto.builder()
                                .commentId(comment.getId())
                                .body(comment.getBody())
                                .build())
                        .toList())
                .build();
    }

    @Transactional
    public PostResponseDto updatePost(UpdatePostRequestDto updatePostRequestDto, Long postId) {
        Post post = findPostOrThrow(postId);

        post.update(updatePostRequestDto.getTitle(), updatePostRequestDto.getContent());
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    @Transactional
    public String deletePost(Long postId) {
        Post post = findPostOrThrow(postId);

        postRepository.delete(post);
        return postId + "번 게시글 삭제 완료";
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> readPostList(){
        // 1. DB에서 모든 post들을 조회
        List<Post> posts = postRepository.findAll();

        // 2. 조회된 post들을 PostResponseDto로 반복문을 통해 변환
        List<PostListResponseDto> responseDtos = posts.stream().map(post ->
                PostListResponseDto.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .commentCount(post.getComments().size())
                        .build()
        ).toList();

        return responseDtos;
    }

    private Post findPostOrThrow(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."));
    }
}


