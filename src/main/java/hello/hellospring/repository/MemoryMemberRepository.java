package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// 강의 진행은 동시성 문제를 고려하지 않았다. 실무에서는 ConcurrentHashMap, AtomicLong 사용을 고려해야 한다.
public class MemoryMemberRepository implements MemberRepository {

    // save()를 할 때 저장할 수 있는 장소
    private static Map<Long, Member> store = new HashMap<>();  // 동시성 문제가 있을 수 있어서 실무에서는 잘 사용하지 않는다.(예제에서만 사용)
    private static long sequence = 0L;                         // 0, 1, 2, ... 키 값을 생성해주는 시퀀스이다.

    @Override
    public Member save(Member member) {
        member.setId(++sequence);           // id를 지정하기 위해 sequence 값을 올려준다.
        store.put(member.getId(), member);  // store에 저장한다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  // null이 반환될 가능성이 있으면 Optional.ofNullable()로 감싸서 보내준다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))  // member.getName의 값이 파라미터로 넘어온 name이랑 같은지 확인하고 같은 경우이면 필터링이 된다.
                .findAny();                                       // 그 중에서 하나라도 찾으면 Optional로 반환한다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());  // store의 회원 목록을 반환한다.
    }

    public void clearStore() {
        store.clear();  // store의 데이터를 지워준다.
    }

}
