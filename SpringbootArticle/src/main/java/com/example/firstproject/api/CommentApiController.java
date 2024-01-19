package com.example.firstproject.api;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;
    //1.댓글 조회
    @GetMapping("/api/articles/{articleId}/comments") //댓글 조회 요청 접수
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){ //CommentDto인 이유를 모르겠음
        // -> db에서 조회한 댓글 엔티티 목록은 List<Comment>지만, 엔티티를 DTO로 변환하면 List<CommentDto>가 되기 때문
        //서비스에 위임
        List<CommentDto> dtos = commentService.comments(articleId); //해당 게시글의 id를 알아야 해당 게시글의 댓글을 가져올 수 있기 때문
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    //2.댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId,
                                             @RequestBody CommentDto dto){ //몇 번 게시글에 어떤 값을 생성하는지 넘겨줌
        //서비스에 위임
        CommentDto createdDto = commentService.create(articleId, dto); //왜 CommentDto지?
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }
    //3.댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id,
                                             @RequestBody CommentDto dto){
        //서비스에 위임
        CommentDto updatedDto = commentService.update(id, dto);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }
    //4.댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id) {
        //서비스에 위임
        CommentDto deletedDto = commentService.delete(id);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }
}
