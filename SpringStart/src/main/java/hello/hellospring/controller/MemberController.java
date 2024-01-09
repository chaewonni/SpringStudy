package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//controller 통해서 외부 요청 받고, 서비스에서 비즈니스 로직 만들고, 리포지토리에 데이터 저장 -> 정형화된 패턴
@Controller
public class MemberController { //얘도 spring 컨테이너가 관리를 하게 됨

    //private final MemberService memberService = new MemberService();
    //new 해서 하면 memberCotroller말고 다른 controller들디 memberService가져다 쓸 수 있음
    //여러개의 인스턴스 생성할 필요 없이 하나 생성해놓고 공용으로 쓰면 됨 -> spring 컨테이너에 하나 등록하고 쓰기 (하나만 등록이됨)
    private MemberService memberService;

//    @Autowired //setter injection 방식
//    public void setMemberService(MemberService memberService){
//        this.memberService = memberService;
//    } // 한번 setting하면 바꿀 일 없는데 public으로 설정해야함 최대한 변경 못하도록 해야하는데..


    @Autowired //그런데 생성자에 AutoWired라고 되어 있으면
    // 스프링이 스프링 컨테이너에 있는 멤버 서비스를 가져다가 연결을 딱 시켜줍니다
    // 생성자에서 이렇게 쓰면 멤버 컨트롤러가 생성이 될 때 스프링 빈에 등록되어 있는 멤버 서비스를 객체를 가져다가 넣어줌 = dependency Injection
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    } //기본적으로 이런 URL 창에 엔터 딱 치는 것은 그냥 GetMapping

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; //회원가입이 끝나고 홈 화면으로 보내버림
    }

    @GetMapping("/members")
    public String list(Model model) { //Model은 스프링에서 컨트롤러와 뷰 간에 데이터를 주고받기 위한 객체
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }


}
