package com.example.springcore1.beanfind;

import com.example.springcore1.member.MemberRepository;
import com.example.springcore1.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanFindTestConfig.class);

    @Test
    @DisplayName("타입으로 빈조회시 같은 타입이 둘이상 있으면, 중복오류가 발생한다.")
    void 중복된타입으로_빈조회테스트1() {
       assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 빈조회시 같은 타입이 둘이상 있으면, 빈이름을 지정하면 된다.")
    void 중복된타입일때_빈이름지정_빈조회테스트() {
        MemberRepository memberRepository = ac.getBean("memberRepository1",MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정타입의 모든 빈을 조회하기")
    void 특정타입_빈조회테스트() {
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        System.out.println("beansOfType = " + beansOfType);

        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key +"  |  value = " +beansOfType.get(key));
        }

        assertThat(beansOfType.size()).isEqualTo(2);
    }


    @Configuration
    static class SameBeanFindTestConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
