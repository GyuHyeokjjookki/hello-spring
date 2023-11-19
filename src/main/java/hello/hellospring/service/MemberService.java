package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//ctrl + shift + T 로 test 코드 생성 가능
//service 어노테이션을 추가해줘야 spring이 멤버 서비스를 감싸주는 것. bean으로 등록이 가능.
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원 가입, 중복 회원 방지
    public Long join(Member member){
        //같은 이름이 있는 중복 회원 x

        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        //orElseGet을 많이 사용, 값이 있는 경우에만 꺼내는 것.
        result.ifPresent(m -> {
            //특정 값이 있는 경우 예외 처리가 발생
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //위의 코드를 리팩토링하여 아래와 같이 작성 가능, 외부 method로 작성
    private void validateDuplicateMember(Member member){
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
     전체 회원 조회
     service class는 비즈니스 적인 용어를 사용해야 함.
    */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findyById(memberId);
    }
}
