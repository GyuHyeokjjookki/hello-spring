package hello.hellospring.service;

import hello.hellospring.domain.Member;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    //같은 멤버 리포지토리가 사용될 수 있도록 member service에 constructor 생성하여 작성.
    //이를 dependency injection이라고 하며 di라고 한다.

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    //테스트 코드는 한글로 이름 지정 가능
    @Test
    void 회원가입() {

        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){

        //given
        Member member1 = new Member();
        member1.setName("srping");

        Member member2 = new Member();
        member2.setName("srping");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //해당 로직을 수행하는데, 예외가 발생하는지를 검증하는 코드, 예외가 발생해야 정상

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*
        try{
            memberService.join(member2);
            fail("예외가 발생해야 합니다.");
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}