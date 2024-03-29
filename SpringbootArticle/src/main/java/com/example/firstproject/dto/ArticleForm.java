package com.example.firstproject.dto; //컨트롤러에서 폼 데이터를 받을 때 DTO에 담아 받음

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {
    private Long id;
    private String title; //제목을 받을 필드
    private String content; //내용을 받을 필드

    //전송받은 제목과 내용을 필드에 저장하는 생성자 추가
//    public ArticleForm(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }

//    //데이터를 잘 받았는지 확인할 toString()메서드 추가
//    @Override
//    public String toString() {
//        return "ArticleForm{" +
//                "title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
