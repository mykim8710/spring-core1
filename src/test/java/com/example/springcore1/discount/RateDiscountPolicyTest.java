package com.example.springcore1.discount;

import com.example.springcore1.member.Grade;
import com.example.springcore1.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RateDiscountPolicyTest {
    private DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP 등급 회원은 주문 시 10% 할인이 적용되어야 한다.")
    void VIP회원_정률할인테스트() {
        // given
        Long memberId = 1L;
        String memberName = "memberVip";
        Grade memberGrade = Grade.VIP;

        Member memberVip = new Member(memberId, memberName, memberGrade);

        // when
        int itemPrice = 10000;
        int discountPrice = discountPolicy.discount(memberVip, itemPrice);
        System.out.println("VIP Member's discountPrice : " +discountPrice);

        // then
        assertThat(discountPrice).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않는다.")
    void 일반회원_정률할인테스트() {
        // given
        Long memberId = 2L;
        String memberName = "memberBasic";
        Grade memberGrade = Grade.BASIC;

        Member memberBasic = new Member(memberId, memberName, memberGrade);

        // when
        int itemPrice = 10000;
        int discountPrice = discountPolicy.discount(memberBasic, itemPrice);
        System.out.println("Basic Member's discountPrice : " +discountPrice);

        // then
        assertThat(discountPrice).isEqualTo(0);
    }
}
