//회원 객체를 저장할 리포지토리
package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원이 저장됨
    //첫 번째 Member는 메서드의 반환 타입을 나타내며, 이 경우 save 메서드가 호출되면 Member 객체를 반환합니다.
    // 두 번째 Member는 메서드의 매개변수 타입을 나타내며, member라는 이름의 Member 객체를 매개변수로 받아 메서드를 호출할 수 있음을 의미
    Optional<Member> findById(Long id); //findById, findByName으로 가져오는 값이 null일 수 있음.
    //요즘에는 null을 처리하는 방법에서 null을 그대로 반환하는 방법 대신 Optional 이라는 걸로 감싸서 반환하는 방법을 많이 선호
    Optional<Member> findByName(String name);
    List<Member> findAll(); //지금까지 저장된 모든 회원들의 리스트를 반환
}

