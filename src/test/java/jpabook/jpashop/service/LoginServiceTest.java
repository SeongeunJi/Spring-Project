package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
class LoginServiceTest {

    @Autowired private LoginService loginService;
    @Autowired private MemberRepository memberRepository;

    @Test
    void loginSuccess () {
        //given
        Member m = new Member();
        m.setEmail("abc@naver.com");
        m.setPassword("abc");
        memberRepository.save(m);

        //when
        Member member = loginService.login(m.getEmail(), m.getPassword()).get();

        //then
        assertEquals(member, m);
    }

    @Test
    void loginFail() {
        //given
        Member m = new Member();
        m.setEmail("abc@naver.com");
        m.setPassword("abc");

        //when
        memberRepository.save(m);

        //then
        assertEquals(loginService.login("avc", "fsdaa"), Optional.empty());

    }
}