package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class ArticleController {
    @Autowired //스프링 부트에선 객체를 만들지 않아도 됨. 스프링 부트가 알아서 객체를 만들기 때문.
    //Autowired 어노테이션을 붙이면 스프링 부트가 미리 생성해놓은 객체를 가져다가 연결해줌 --> 의존성 주입(DI)
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create") //<form>태그에 action="/articles/create"로 설정했으므로
    public String createArticle(ArticleForm form){
        log.info(form.toString());
//        System.out.println(form.toString());
        //1.DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString());
//        System.out.println(article.toString());
        //2.리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article); //article 엔티티를 저장해 saved 객체에 반환
        log.info(saved.toString());
//        System.out.println(saved.toString());
        return "redirect:/articles/" + saved.getId(); //리다이렉트를 작성할 위치
    }

    @GetMapping("/articles/{id}") //데이터 조회 요청 접수
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id); //id를 잘 받았는지 확인하는 로그 찍기
        //1.id를 조회해 데이터 가져오기
//        Optional<Article> articleEntity = articleRepository.findById(id);
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id); //댓글
        //2.모델에 데이터 등록하기
        model.addAttribute("article", articleEntity); //article이란 이름으로 articleEntity 객체를 등록 --> 이 부분 어려웠음
        model.addAttribute("commentDtos", commentDtos); //댓글
        //3.뷰 페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){//가져온 데이터를 받은 articleEntityList를 뷰 페이지로 전달할 때는 모델을 사용
        //1.모든 데이터 가져오기
        List<Article> articleEntityList = articleRepository.findAll();
        //2.모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);
        //3.뷰 페이지 설정하기
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //모델에 데이터 등록하기
        model.addAttribute("article",articleEntity);
        //뷰 페이지 설정하기
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){ //매개변수로 DTO 받아오기
        log.info(form.toString());
        //1.DTO를 엔티티로 변환하기
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        //2.엔티티를 DB에 저장하기
        //2-1.DB에서 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        //2-2.기존 데이터 값을 갱신하기
        if(target != null){
            articleRepository.save(articleEntity);
        }
        //3.수정 결과 페이지로 리다이렉트하기
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        log.info("삭제 요청이 들어왔습니다!!");
        //1.삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        //2.대상 엔티티 삭제하기
        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제됐습니다!"); //한 번 쓰고 사라지는 휘발성 데이터를 등록하는 것
            //msg: 넘겨주려는 키 문자열, "삭제됐습니다!":넘겨주려는 값 객체
        }
        //3.결과 페이지로 리다이렉트하기
        return "redirect:/articles";
    }

}
