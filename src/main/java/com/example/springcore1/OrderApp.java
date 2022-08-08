package com.example.springcore1;

import com.example.springcore1.config.AppConfig;
import com.example.springcore1.member.Grade;
import com.example.springcore1.member.Member;
import com.example.springcore1.member.MemberService;
import com.example.springcore1.order.Order;
import com.example.springcore1.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
        // 최초 객체 생성
        // MemberService memberService = new MemberServiceImpl();
        // OrderService orderService = new OrderServiceImpl();

        // DI 주입 코드로 수정, AppConfig가 객체 인스턴스를 클라이언트 코드 대신 생성해서 클라이언트 코드에 의존관계를 주입
        // AppConfig appConfig = new AppConfig();
        // MemberService memberService = appConfig.memberService();
        // OrderService orderService = appConfig.orderService();

        // 스프링 적용으로 수정
        // ApplicationContext를 스프링 컨테이너라 한다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);


        // 회원객체 생성 및 회원가입
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.joinMember(member);

        // 주문생성
        String itemName = "ItemA";
        int itemPrice = 2000;

        Order order = orderService.createOrder(memberId, itemName, itemPrice);
        System.out.println(order);
    }
}
