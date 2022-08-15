package com.example.springcore1.member;

import com.example.springcore1.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberServiceTest {
    // MemberService memberService = new MemberServiceImpl();
    MemberService memberService;

    // 각 테스트 실행전 무조건 실행
    @BeforeEach
    public void beforeEach() {
//        AppConfig appConfig = new AppConfig();
//        memberService = appConfig.memberService();

        // 스프링 컨테이너 적용
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        memberService = applicationContext.getBean("memberService", MemberService.class);
    }

    @Test
    void 회원가입테스트() {
        // given :  ~ 이 주어지고
        Long memberId = 1L;
        String memberName = "memberA";
        Grade memberGrade = Grade.VIP;
        Member member = new Member(memberId, memberName, memberGrade);

        // when  :  ~ 이것을 실행했을때
        memberService.joinMember(member);

        // then  :  ~ 결과가 이것이 나와야 된다.
        Assertions.assertThat(member).isEqualTo(memberService.findMember(memberId));
    }
}
