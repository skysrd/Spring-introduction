package Skysrd.Springintroduction.service;

import Skysrd.Springintroduction.domain.Member;
import Skysrd.Springintroduction.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    public void registrationTest() throws Exception {
        //given
        Member member = new Member();
        member.setName("registerTest");

        //when
        Long saveId = memberService.join(member);

        //Then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void duplicateUserValidateTest() throws Exception {
        //given
        Member testMember1 = new Member();
        testMember1.setName("testMember1");

        Member testMember2 = new Member();
        testMember2.setName("testMember2");

        //when
        memberService.join(testMember1);
        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class,
                () -> memberService.join(testMember2)); // 예외가 발생해야 함

        //then
        assertThat(illegalStateException.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
