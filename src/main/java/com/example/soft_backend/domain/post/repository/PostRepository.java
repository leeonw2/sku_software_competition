package com.example.soft_backend.domain.post.repository;

import com.example.soft_backend.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    // 1. 게시글 제목으로 모든 Post 찾기
    // List<Post> findByTitle(String title);
    // ㄴ> Optional이 아니라 List형 사용함. 제목이 같은 글들이 여러개 있을 수도 있기 때문임
    // ㄴ> 만약 Optional로 받았는데 탐색한 값이 여러개라면, 오류가 발생한다!

    // 2. 게시글 제목으로 게시글 데이터가 있는지 확인하는 함수
    //boolean existsByTitle(String title);

    // 3. 게시글 조회 횟수가 N인 게시글들을 삭제하는 함수
    //Long deleteByViews(Long views);
    // ㄴ> 삭제된 개수가 몇개인지 반환함

    Optional<Post> findById(Long id);
    boolean existsById(Long id);
    void deleteById(Long id);

}
