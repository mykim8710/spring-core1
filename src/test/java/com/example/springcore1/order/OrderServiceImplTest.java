package com.example.springcore1.order;

import com.example.springcore1.discount.FixDiscountPolicy;
import com.example.springcore1.member.Grade;
import com.example.springcore1.member.Member;
import com.example.springcore1.member.MemberRepository;
import com.example.springcore1.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        // given
        MemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1l, "A", Grade.VIP));

        // when
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);

        // then
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}