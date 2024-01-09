package com.example.firstproject.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi") //URL 요청 접수 (클라이언트로부터 "/hi"라는 요청을 받아 접수
    public String niceToMeetYou(Model model) { //뷰 템플릿 페이지에서 사용할 변수를 등록하기 위해 모델 객체를 매개변수로 가져옴
        model.addAttribute("username","hongpark");
        return "greetings"; //greeting.mustache 파일 반환
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname", "홍길동");
        return "goodbye"; //컨트롤러는 그대로 사용하기로 했지만 뷰 템플릿 페이지는 새로 만들어야 함
    }
}
