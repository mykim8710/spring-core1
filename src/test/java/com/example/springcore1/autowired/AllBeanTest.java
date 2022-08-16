package com.example.springcore1.autowired;

import com.example.springcore1.AutoAppConfig;
import com.example.springcore1.discount.DiscountPolicy;
import com.example.springcore1.member.Grade;
import com.example.springcore1.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {
    @Test
    void findAllBean() {
        // given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when1
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        // then1
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        // when2
        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");

        // then2
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    static class DiscountService {
        private Map<String, DiscountPolicy> discountPolicyMap;
        private List<DiscountPolicy> discountPolicies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> discountPolicyMap, List<DiscountPolicy> discountPolicies) {
            this.discountPolicyMap = discountPolicyMap;
            this.discountPolicies = discountPolicies;
            System.out.println("discountPolicyMap = " + discountPolicyMap);
            System.out.println("discountPolicies = " + discountPolicies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = discountPolicyMap.get(discountCode);

            System.out.println("discountCode = " + discountCode);
            System.out.println("discountPolicy = " + discountPolicy);

            return discountPolicy.discount(member,price);
        }
    }
}
