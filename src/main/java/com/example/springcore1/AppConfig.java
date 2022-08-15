package com.example.springcore1;

import com.example.springcore1.discount.DiscountPolicy;
import com.example.springcore1.discount.RateDiscountPolicy;
import com.example.springcore1.member.MemberRepository;
import com.example.springcore1.member.MemberService;
import com.example.springcore1.member.MemberServiceImpl;
import com.example.springcore1.member.MemoryMemberRepository;
import com.example.springcore1.order.OrderService;
import com.example.springcore1.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 객체의 생성과 연결은 AppConfig 가 담당한다.
// DIP 완성: MemberServiceImpl 은 MemberRepository인 추상에만 의존하면 된다. 이제 구체 클래스를 몰라도 된다.
// 관심사의 분리: 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리되었다.
// appConfig 객체는 memoryMemberRepository 객체를 생성하고 그 참조값을 memberServiceImpl을 생성하면서 생성자로 전달한다.
// 클라이언트인 memberServiceImpl 입장에서 보면 의존관계를 마치 외부에서 주입해주는 것 같다고 해서 DI(Dependency Injection) 우리말로 의존관계 주입 또는 의존성 주입이라 한다.

// 스프링 적용
@Configuration  // AppConfig에 설정을 구성한다는 뜻의 annotation
public class AppConfig {

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService  -> new MemoryMemberRepository()

    // 예상
    // Call AppConfig.memberService
    // Call AppConfig.memberRepository
    // Call AppConfig.memberRepository
    // Call AppConfig.orderService
    // Call AppConfig.memberRepository

    // 실제
    // Call AppConfig.memberService
    // Call AppConfig.memberRepository
    // Call AppConfig.orderService


    @Bean
    // 스프링 컨테이너에 스프링 빈으로 등록한다.
    // 기본적으로 메서드 이름으로 빈이 등록된다.
    public MemberService memberService() {
        System.out.println("Call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("Call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("Call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixedDiscountPolicy();
        return new RateDiscountPolicy();
        // AppConfig 에서 할인 정책 역할을 담당하는 구현을 FixDiscountPolicy -> RateDiscountPolicy 객체로 변경
        // 클라이언트 코드인 OrderServiceImpl 를 포함해서 사용 영역의 어떤 코드도 변경할 필요가 없다.
        // 구성 영역은 당연히 변경된다. 구성 역할을 담당하는 AppConfig를 애플리케이션이라는 공연의 기획자로 생각하자. 공연 기획자는 공연 참여자인 구현 객체들을 모두 알아야 한다
    }
}
