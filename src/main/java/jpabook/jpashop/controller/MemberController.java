package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.consts.Country;
import jpabook.jpashop.exception.DuplicateMemberEmailException;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final Map<String, Country> countryMap;

    @GetMapping("/members/new")
    public String createForm(@ModelAttribute MemberForm form, Model model) {
        model.addAttribute("countryMap", countryMap);
        log.info("countryMap:{}",countryMap);
        return "/members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String joinMember(@Validated @ModelAttribute MemberForm form, BindingResult bindingResult, Model model) {
        if (!form.getPassword().equals(form.getPasswordCheck())) {
            bindingResult.reject(null, "The passwords do not match.");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("countryMap", countryMap);
            return "/members/createMemberForm";
        }

        Address address = new Address(form.getCountry(), form.getCity(), form.getStreet(), form.getZip());
        Member member = new Member();
        member.setName(form.getName());
        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());
        member.setAddress(address);

        try {
            memberService.join(member);
        } catch(DuplicateMemberEmailException e) {
            bindingResult.rejectValue("email",null, e.getMessage());
            return "/members/createMemberForm";
        }
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "/members/memberList";
    }

}
