package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository; //게시글 리파지터리가 있어야 댓글을 생성할 때 대상 게시글의 존재 여부를 파악할 수 있기 때문

    public List<CommentDto> comments(Long articleId) {
        /*//1. 댓글 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        //2. 엔티티 -> DTO 변환
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for (int i = 0; i < comments.size(); i++){ //조회한 댓글 엔티티 수만큼 반복하기
            Comment c = comments.get(i); //조회한 댓글 엔티티 하나씩 가져오기
            CommentDto dto = CommentDto.createCommentDto(c); //엔티티를 DTO로 변환
            dtos.add(dto); //변환한 DTO를 dtos 리스트에 삽입
        }*/
        //3. 결과 반환
        return commentRepository.findByArticleId(articleId)
                .stream() //저장된 요소들을 하나씩 참조하며 반복처리할 때 사용
                .map(comment -> CommentDto.createCommentDto(comment))//엔티티를 DTO로 매핑
                // --> 여기까지만 하면 List<CommentDto>타입을 반환해야 하는데 Stream<CommentDto> 타입을 반환하여 빨간줄
                .collect(Collectors.toList()); //스트림을 리스트로 변환
    }

    @Transactional //create()메서드는 DB내용을 바꾸기 때문에 실패할 경우를 대비해야 함, 문제가 생겼을 때 롤백
    public CommentDto create(Long articleId, CommentDto dto) {
        //1.게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! "+ "대상 게시글이 없습니다.")); //부모 게시글 가져오기
        //2.댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);
        //3.댓글 엔티티를 DB에 저장
        Comment created = commentRepository.save(comment);
        //4.DTO로 변환해 반환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        //1.댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).
                orElseThrow(()-> new IllegalArgumentException("댓글 수정 실패! " + "대상 댓글이 없습니다."));
        //2.댓글 수정
        target.patch(dto);
        //3.DB로 갱신
        Comment updated = commentRepository.save(target);
        //4.댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        //1.댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).
                orElseThrow(()-> new IllegalArgumentException("댓글 삭제 실패! " + "대상이 없습니다."));
        //2.댓글 삭제
        commentRepository.delete(target);
        //3.삭제 댓글을 dto로 변환 및 반환
        return CommentDto.createCommentDto(target);
    }
}
