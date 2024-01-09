package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {

    // 저장소
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;

    //@Autowired //MemberService 등록하면서 이 생성자도 호출함
    //그런데 그때 이 오토 와이어드가 있으면 아 너는 멤버 리포지토리가 필요하구나고 해서
    //스프링 컨테이너에는 멤버 리포지토리를 넣어줌
    //memoryMemberRepository가 구현체로 있어서 이 memoryMemberRepository를 서비스에 주입해줌
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    public static void main(String[] args) {
//        MemberService memberService = new MemberService(); //동작하지 않음
//    } 스프링 빈으로 등록하지 않고 내가 직접 생성한 객체에서는 동작하지 않는다.

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원X
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
            validateDuplicateMember(member); //중복 회원 검증
            memberRepository.save(member);
            return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> { //이름이 같은 회원이 존재하면
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
            return memberRepository.findAll();
    }
    //id로 회원조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
