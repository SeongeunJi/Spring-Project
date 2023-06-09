package jpabook.jpashop.service;

import jakarta.annotation.PostConstruct;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.exception.DuplicateMemberEmailException;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
// JPA의 모든 DB 변경은 반드시 @Transactional이 있어야 함.
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }
    private void validateDuplicateMember(Member member) {
        if (memberRepository.findByEmail(member.getEmail()).isPresent())  {
            throw new DuplicateMemberEmailException("The given email has already exist.");
        }
    }

    // 회원 통합 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //회원 단건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
