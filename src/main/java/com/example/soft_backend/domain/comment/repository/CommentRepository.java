package com.example.soft_backend.domain.comment.repository;

import com.example.soft_backend.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 1. 게시글 제목으로 모든 Post 찾기
//    List<Comment> findByAuthor(String author);
    // ㄴ> Optional이 아니라 List형 사용함. 제목이 같은 글들이 여러개 있을 수도 있기 때문임
    // ㄴ> 만약 Optional로 받았는데 탐색한 값이 여러개라면, 오류가 발생한다!

    // 2. 게시글 제목으로 게시글 데이터가 있는지 확인하는 함수
//    boolean existsByAuthor(String author);

    // 3. 게시글 조회 횟수가 N인 게시글들을 삭제하는 함수
//    Long deleteByViews(Long views);
    // ㄴ> 삭제된 개수가 몇개인지 반환함

}