package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto { //Comment(댓글 엔티티)를 담을 그릇
    private Long id;
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentDto createCommentDto(Comment comment) { //public static: 객체 생성 없이 호출 가능한 메서드 (생성 메서드)
        return new CommentDto( //메서드의 반환 값이 댓글 DTO가 되도록 CommentDto의 생성자 호출
                comment.getId(), //댓글 엔티티의 id
                comment.getArticle().getId(), //댓글 엔티티가 속한 부모 게시글의 id
                comment.getNickname(), //댓글 엔티티의 nickname
                comment.getBody() //댓글 엔티티의 body
        );
    }

}
