package com.example.springcore1.discount;

import com.example.springcore1.member.Grade;
import com.example.springcore1.member.Member;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy {
    private int discountPercent = 10; // 10% 할인

    @Override
    public int discount(Member member, int itemPrice) {
        if(member.getGrade() == Grade.VIP) {
            return itemPrice * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
