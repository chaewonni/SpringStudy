package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service //서비스 객체 생성
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository; //게시글 리파지터리 객체 주입

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if (article.getId() != null) { //id는 데이터를 생성할 때 굳이 넣을 필요가 없으므로
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto){
        //1.DTO -> 엔티티 변환하기
        Article article = dto.toEntity(); //dto를 엔티티로 변환
        log.info("id: {}, article: {}", id, article.toString());
        //2.타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);
        //3.잘못된 요청 처리하기
        if (target == null || id != article.getId()) {
            //400, 잘못된 요청 응답!
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); 이건 컨트롤러의 역할이므로
            return null; //응답은 컨트롤러가 하므로 여기서는 null 반환
        }
        //4.업데이트하기
        target.patch(article); //기존 데이터에 새 데이터 붙이기 //!!(일부 데이터만 수정할 때)!!
        Article updated = articleRepository.save(target); //article 엔티티 DB에 저장
        return updated;
    }

    public Article delete(Long id) {
        //1.대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //2.잘못된 요청 처리하기
        if(target == null){
            return null;
        }
        //3.대상 삭제하기
        articleRepository.delete(target);
        return target; //DB에서 삭제한 대상을 컨트롤러에 반환
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        //1.dto 묶음을 엔티티 묶음으로 변환하기
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        //2.엔티티 묶음을 DB에 저장하기
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        //3.강제 예외 발생시키기
        articleRepository.findById(-1L) //id가 -1인 데이터 찾기
                .orElseThrow(() -> new IllegalArgumentException("결제 실패!!")); //찾는 데이터가 없으면 예외 발생
        //IllegalArgumentException은 전달값이 없거나 유효하지 않은 경우를 뜻함
        //4.결과 값 반환하기
        return articleList;
    }
}
