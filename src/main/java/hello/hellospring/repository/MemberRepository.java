package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findyById(Long id);
    Optional<Member> findByName(String name);
    //data가 없는 경우 null을 retrun 해야하는데 이런 경우에 optional을 사용한다.
    List<Member> findAll();
}
