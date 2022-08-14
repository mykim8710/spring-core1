package com.example.springcore1.order;

import com.example.springcore1.discount.DiscountPolicy;
import com.example.springcore1.member.Member;
import com.example.springcore1.member.MemberRepository;

// OrderServiceImpl 은 기능을 실행하는 책임만 지면 된다
public class OrderServiceImpl implements OrderService {
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    //private final DiscountPolicy discountPolicy = new FixedDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // 할인 정책을 변경하려면 클라이언트인 OrderServiceImpl 코드를 고쳐야 한다.
    // 즉, FixDiscountPolicy를 RateDiscountPolicy로 변경하는 순간 OrderServiceImpl의 소스 코드도 함께 변경해야 한다!(OCP 위반)

    // private DiscountPolicy discountPolicy;
    // DIP를 위반하지 않도록 인터페이스에만 의존하도록 의존관계를 변경
    // 구현체가 없으므로 실행을 해보면 NPE(null pointer exception)가 발생
    // 이 문제를 해결하려면 누군가가 클라이언트인 OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해주어야 한다.


    // 생성자를 통한 주입, DIP를 지킨다, 철저하게 interface에만 의존하고 있다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /**
    * 주문생성
    * 회원 id, 상품명, 상품가격을 입력하면 주문서비스에서 회원을 조회하고 할인을 적용하여 주문결과를 반환
    **/

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 주문 생성 요청이 오면, 회원 정보를 조회하고, 할인 정책을 적용한 다음 주문 객체를 생성해서 반환한다.

        // 회원조회
        Member member = memberRepository.findById(memberId);

        // 할인정책 : 할인적용 금액 확인
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 주문내역 리턴
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }


    // Test용 : @Configuration과 싱글톤
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
