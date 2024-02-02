package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {  // 다른 곳에서 사용할 것이 아니기 때문에 public으로 하지 않아도 된다.

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach  // 메서드 실행이 끝날 때마다 어떤 동작을 하게 해주는 callback 메서드
    public void afterEach() {
        repository.clearStore();  // 각 테스트 메서드가 끝날 때마다 store의 데이터를 지워준다.
    }

    @Test
    public void save() {
        // given
        Member member = new Member();
        member.setName("spring");  // 회원 이름 저장

        // when
        repository.save(member);   // 리포지토리에 회원 저장

        // then
        Member result = repository.findById(member.getId()).get();  // findById로 저장된 id를 찾아 확인한다. 반환타입이 Optional이기 때문에 get을 사용해서 값을 꺼낼 수 있다.
                                                                    // get을 사용하는게 좋은 방법은 아니지만 테스트 코드에서는 괜찮음.

//        System.out.println("result = " + (result == member));     // result와 member랑 동일하면 true를 출력한다. 하지만 이런식으로 계속 확인하기 비효율적임.
//        Assertions.assertEquals(member, result);                  // jupiter의 Assertions 사용, (기대하는 값, 결과 값) 실패할 경우 에러가 발생한다.
        assertThat(member).isEqualTo(result);                       // assertj의 Assertions 사용
    }

    @Test
    public void findByName() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // when
        Member result = repository.findByName("spring1").get();

        // then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // when
        List<Member> result = repository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);

    }

}
