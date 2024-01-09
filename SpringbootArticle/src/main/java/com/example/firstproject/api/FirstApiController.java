package com.example.firstproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//REST 컨트롤러는 JSON이나 텍스트 같은 데이터를 반환하는 반면 일반 컨트롤러는 뷰 페이지를 반환함
@RestController
public class FirstApiController {
    @GetMapping("/api/hello")
    public String hello(){
        return "hello world!";
    }
}
