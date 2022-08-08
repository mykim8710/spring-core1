package com.example.springcore1.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService = new MemberServiceImpl();

    @Test
    void 회원가입테스트() {
        // given :  ~ 이 주어지고
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when  :  ~ 이것을 실행했을때
        memberService.joinMember(member);

        // then  :  ~ 결과가 이것이 나와야 된다.
        Assertions.assertThat(member).isEqualTo(memberService.findMember(1L));
    }
}
