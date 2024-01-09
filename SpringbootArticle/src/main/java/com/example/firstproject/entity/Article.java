package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity //DB가 해당 객체를 인식하도록 붙인 것 (해당 클래스로 테이블을 만들라는 뜻)
public class Article {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 id 자동 생성
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

    public void patch(Article article) { //수정할 내용이 있는 경우에만 동작
        if(article.title != null)
            this.title = article.title;
        if(article.content != null)
            this.content = article.content;
    }

//    public Long getId(){
//        return id;
//    }

    //    public Article(Long id, String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }
//
//    @Override
//    public String toString() {
//        return "Article{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }
}
