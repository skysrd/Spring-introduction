package Skysrd.Springintroduction.service;

import Skysrd.Springintroduction.domain.Member;
import Skysrd.Springintroduction.repository.MemberRepository;
import Skysrd.Springintroduction.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {

    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member) {
        //중복 회원 검증
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //중복 회원 검증
        private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
        }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //Id 회원 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
