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
public class MemberController {

    // 스프링 컨테이너에 등록해서 사용하는 것이 좋다.
    private final MemberService memberService;

    @Autowired  // 스프링 컨테이너에 있는 멤버 서비스를 연결 시켜준다. 이를 DI(Dependency Injection)이라고 한다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        System.out.println("memberService = " + memberService.getClass());  // 프록시 사용 확인
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";  // 회원가입이 완료되면 홈화면으로 이동하게 한다.
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findByMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
