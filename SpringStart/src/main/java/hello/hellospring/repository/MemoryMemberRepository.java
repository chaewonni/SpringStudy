package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    //Long 타입의 키와 Member 객체를 값으로 가지는 HashMap 객체로 초기화
    private static long sequence = 0L;
    //sequence: key값을 생성해줌

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        //member 객체의 id를 키로 사용하고, member 객체 자체를 값으로 저장합니다.
        // 이렇게 하면 id를 사용하여 회원을 조회할 수 있게 됩니다.
        return member;
    }

    //저장소에서 id를 찾아 member 객체 반환
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //null이어도 감쌀 수 있음
    }

    //저장소에서 name을 찾아 member 객체 반환
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }
    //스트림을 통해 맵에 저장된 모든 Member 객체에 접근
    //스트림의 filter 메서드를 사용하여 조건을 만족하는 요소만 남깁니다. 여기서는 회원의 이름이 주어진 name과 동일한 경우만을 필터링
    //Optional로 'name'과 일치하는 회원을 못찾았을 때엔 빈 'Optional'반환
    //.findAny(): 필터링된 요소 중 하나를 찾음

    //저장된 모든 회원의 member List 반환
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
