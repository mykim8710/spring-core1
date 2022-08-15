package com.example.springcore1;

import com.example.springcore1.member.Grade;
import com.example.springcore1.member.Member;
import com.example.springcore1.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        // 최초 객체 생성
        // MemberService memberService = new MemberServiceImpl();

        // DI 주입 코드로 수정, AppConfig가 객체 인스턴스를 클라이언트 코드 대신 생성해서 클라이언트 코드에 의존관계를 주입
        // AppConfig appConfig = new AppConfig();
        // MemberService memberService = appConfig.memberService();

        // 스프링 적용 - @Annotation 기반의 AppConfig.class
        // ApplicationContext를 스프링 컨테이너라 한다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);


        // 스프링 적용 - XML기반의 appConfig.xml
        // ApplicationContext applicationContext = new GenericXmlApplicationContext("appConfig.xml");
        // MemberService memberService = applicationContext.getBean("memberService", MemberService.class);


        // join Member Test
        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.joinMember(memberA);

        // find Member Test
        Member findMember = memberService.findMember(1L);
        System.out.println("new Member : " +memberA.getName());
        System.out.println("find Member : " +findMember.getName());
    }
}
