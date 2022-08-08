package com.example.springcore1.order;

import com.example.springcore1.config.AppConfig;
import com.example.springcore1.member.Grade;
import com.example.springcore1.member.Member;
import com.example.springcore1.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderServiceTest {
//    MemberService memberService = new MemberServiceImpl();
//    OrderService orderService = new OrderServiceImpl();

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
//        AppConfig appConfig = new AppConfig();
//        memberService = appConfig.memberService();
//        orderService = appConfig.orderService();

        // 스프링 컨테이너 적용
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        memberService = applicationContext.getBean("memberService", MemberService.class);
        orderService = applicationContext.getBean("orderService", OrderService.class);
    }

    @Test
    void 주문서비스테스트() {
        // given
        Long memberId = 1L;
        String memberName = "memberA";
        Grade memberGrade = Grade.VIP;

        Member member = new Member(memberId, memberName, memberGrade);
        memberService.joinMember(member);

        // when
        String itemName = "itemA";
        int itemPrice = 10000;

        Order order = orderService.createOrder(memberId, itemName, itemPrice);

        // then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}
