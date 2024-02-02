package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);                // 회원이 저장소에 저장된다.
    Optional<Member> findById(Long id);        // id로 회원을 찾는다.
    Optional<Member> findByName(String name);  // name으로 회원을 찾는다.
    List<Member> findAll();                    // 저장된 모든 회원의 리스트를 반환해준다.

}
