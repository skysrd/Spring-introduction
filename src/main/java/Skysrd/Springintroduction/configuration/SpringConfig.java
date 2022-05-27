package Skysrd.Springintroduction.configuration;

import Skysrd.Springintroduction.repository.MemberRepository;
import Skysrd.Springintroduction.repository.MemoryMemberRepository;
import Skysrd.Springintroduction.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Member;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
