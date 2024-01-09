package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleService articleService; //서비스 객체 주입
//    @Autowired //의존성 주입 (게시글 리파지터리 주입)
//    private ArticleRepository articleRepository;
    //GET
    @GetMapping("/api/articles") //URL 요청 접수
    public List<Article> index(){ //index()메서드 정의
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleService.show(id);
    }

    //POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){
        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          //Article을 ResponseEntity에 담아서 반환해야만 반환하는 데이터에 상태코드를 실어보낼 수 있으므로
                          @RequestBody ArticleForm dto){
        Article updated = articleService.update(id, dto); // 서비스를 통해 게시글 수정
        return (updated != null) ? //updated에 내용이 있다면
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }//컨트롤러는 컨트롤러의 역할만. 컨트롤러는 서비스에 무슨 지시를 하고 무슨 데이터를 받아 오는지만 알면 됨. 실제 작업은 서비스에 맡김.

    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/api/transaction-test") //여러 게시글 생성 요청 접수
    public ResponseEntity<List<Article>> transactionTest
            (@RequestBody List<ArticleForm> dtos){
        List<Article> createdList = articleService.createArticles(dtos); //서비스 호출
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
