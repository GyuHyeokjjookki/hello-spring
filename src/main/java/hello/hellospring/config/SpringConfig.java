package hello.hellospring.config;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    //이렇게 해주면 스프링에서 빈으로 등록해줌.

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    //service와 repository를 스프링 빈으로 등록해주는 것

}
