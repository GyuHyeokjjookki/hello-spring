package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    //실무에서 이런 공유되는 변수는 ConcurrentHashMap을 사용해야 하지만 여기는 예시니 그냥 HashMap을 사용
    private static Long sequence = 0L;
    //실무에서는 동시성 문제를 고려해서 AtomicLong 이렇게 사용해야 함.
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        //id는 시스템에서 결정하는 식별자이고, name은 입력으로 받는 것.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    //null에 대비하기 위해 optional ofnullable로 감싸주면 됨.

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    //구현한 기능들을 검증하기 위해 테스트 코드를 작성

    public void clearStore(){
        store.clear();
    }
}
