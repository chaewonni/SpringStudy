package hello.core.member;


import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//단위 테스트: 스프링이나 어떤 컨테이너의 도움 없이 순수하게 자바 코드로 테스트 --> 삽시간에 끝남
public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach //테스트를 실행하기 전에 무조건 실행됨
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
