package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    public Optional<Member> login(String email, String password) {
        return memberRepository.findByEmail(email)
                        .filter(m -> m.getPassword().equals(password))
                        .or(Optional::empty);
    }
}
