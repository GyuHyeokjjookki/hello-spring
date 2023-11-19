package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
        //test가 독립적으로 실행될 수 있도록 생성된 클래스(공용 데이터)를 비워야함.
        //clear 함수를 MemoryMemberRepository에 작성
    }
    //테스트를 만들어보고 구현을 하는 것을 TDD라고 함.

    @Test//test로 실행이 가능
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findyById(member.getId()).get();
        //optional은 get으로 값을 꺼낼 수 있다.
        //Assertions.assertEquals(member, result);
        //두 가지 값이 맞는지 확인 가능
        //Assertions.assertThat(member).isEqualTo(result);
        assertThat(member).isEqualTo(result);
        //assert를 더 쉽게 사용할 수 있음. Assertions는 static method 기에 import Assertions.*;로 사용가능
    }

    @Test
    public void findByName(){

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //member 이름이 겹치는 경우 shift + F6으로 한번에 수정가능

        Member result = repository.findByName("spring1").get();
        //assertThat으로 해당 method의 검증 가능, 테스트는 전체를 한번에 검증이 가능
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
        //test는 순서가 보장이 안됨. 그래서 각 테스트는 서로 의존성을 가지면 안됨. 독립적으로 실행될 수 있도록.
    }
}
