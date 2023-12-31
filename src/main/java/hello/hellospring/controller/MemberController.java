package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
//controller annotation이 있으면, 이 controller가 spring이 뜰때 객체를 생성하여 spring에 넣어두고 관리함.
//이를 spring bean이 관리된다고 한다. 이래서 spring에 관련된 기능이 동작할 수 있는것.
public class MemberController {

    private final MemberService memberService;

    //autoWired로 spring 컨테이너에는 객체를 하나만 생성하여 그 객체들을(여러 곳에서 member service를 호출하기 때문) 연결시켜주는 역할
    //autoWired를 호출 시에 spring bean에 등록되어 있는 객체를 연결해주는 것. 이를 의존관계 주입이라고 함.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    //최초에는 memberService에서 오류가 발생하는데, 이는 spring 컨테이너에 객체가 등록되지 않아 연결을 하지 못하는 것. 이를 해결하기 위해 member service에 @Service 어노테이션을 추가해준다.

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }
    //특별한 역할 없이 templates을 호출해주기만 하는 것. html이 뿌려지기만 하는 것.

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }
    //Post는 주로 데이터를 전달하거나, 저장을 할 떄 사용을 함. 등록할 때 씀. Get은 조회에 사용

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        //key가 members임, 여기에 모든 회원이 저장되어 있고 루프를 돌면서 출력하는 것.
        //현재는 회원이 메모리에만 있기 때문에 서버 재시작시 전부 사라짐.
        return "members/memberList";
    }
}
