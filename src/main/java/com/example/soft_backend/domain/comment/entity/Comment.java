//import com.example.mentor_mentee.domain.post.entity.Post;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Getter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name= "comment")
//public class Comment {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name= "id")
//    private Long id;
//
//    @Column(nullable= false)
//    private String author;
//
//    @Column(nullable= false)
//    private String content;
//
//    @Column(nullable= false)
//    @Builder.Default
//    private Long views = 0L;
//
//    // N:1 관계
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "post_id", nullable = false) // FK 생성
//    private Post post;
//}
package com.example.soft_backend.domain.comment.entity;
import com.example.soft_backend.domain.post.entity.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "body", nullable = false, length = 300)
    private String body;

    // 추가된 부분
    // N:1 관계
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", nullable = false) // FK 생성
    private Post post;
}