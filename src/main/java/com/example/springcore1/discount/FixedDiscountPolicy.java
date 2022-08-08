package com.example.springcore1.discount;

import com.example.springcore1.member.Grade;
import com.example.springcore1.member.Member;

public class FixedDiscountPolicy implements DiscountPolicy {
    private int fixedDiscountAmount = 1000; // VIP 등급은 항상 1000원씩 정액할인을 해준다.

    @Override
    public int discount(Member member, int itemPrice) {
        if(member.getGrade() == Grade.VIP) {
            return fixedDiscountAmount;
        } else {
            return 0;
        }
    }
}
