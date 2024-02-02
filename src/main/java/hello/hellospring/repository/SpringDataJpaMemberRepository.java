package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// SpringDataJpaMemberRepository 인터페이스가 JpaRepository를 갖고 있으면 구현체를 자동으로 만들어준다. (스프링 빈에 자동으로 등록한다.)
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {  // jpa는 JpaRepository를 상속 받아야 한다.


    @Override
    Optional<Member> findByName(String name);

}
