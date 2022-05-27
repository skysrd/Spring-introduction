package Skysrd.Springintroduction.repository;

import Skysrd.Springintroduction.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        memoryMemberRepository.clearStore();
    }

    @Test
    public void save() {
        // given
        Member member = new Member();
        member.setName("spring");

        //when
        memoryMemberRepository.save(member);

        //then
        Member result = memoryMemberRepository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("membertest1");
        memoryMemberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("membertest2");
        memoryMemberRepository.save(member2);

        //when
        Member result = memoryMemberRepository.findByName("membertest1").get();

        //then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        //given
        Member testMember1 = new Member();
        testMember1.setName("testMember1");
        memoryMemberRepository.save(testMember1);

        Member testMember2 = new Member();
        testMember2.setName("testMember2");;
        memoryMemberRepository.save(testMember2);

        //when
        List<Member> result = memoryMemberRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
    }
}
