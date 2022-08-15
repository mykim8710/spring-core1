package com.example.springcore1.singleton;

import com.example.springcore1.AppConfig;
import com.example.springcore1.member.MemberRepository;
import com.example.springcore1.member.MemberServiceImpl;
import com.example.springcore1.order.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

// given
// when
// then

public class ConfigurationSingletonTest {

    @Test
    @DisplayName("@Configuration과 싱글톤을 테스트하는 코드")
    void configuration_싱글톤테스트() {
        // given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);


        // when
        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository);
        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        

        // then
        assertThat(memberRepository1).isSameAs(memberRepository);
        assertThat(memberRepository2).isSameAs(memberRepository);
    }

    @Test
    @DisplayName("@Configuration과 바이트코드 조작에 대한 테스트하는 코드")
    void configuration_동작테스트() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean.getClass() = " + bean.getClass()); // $$EnhancerBySpringCGLIB$$349624d9 ??
    }
}
