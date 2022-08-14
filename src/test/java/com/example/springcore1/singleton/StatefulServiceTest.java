package com.example.springcore1.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void 싱글톤_stateful테스트() {
        ApplicationContext ac =  new AnnotationConfigApplicationContext(StatefulServiceTestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // 문제가 있는 코드에 대한 테스트
//        statefulService1.order("userA", 10000);
//        statefulService2.order("userB", 20000);
//
//        // ThreadA: userA 주문 금액 조회
//        int price = statefulService1.getPrice();
//
//        // ThreadA: userA는 10000원을 기대했지만, 기대와 다르게 20000원 출력
//        System.out.println("price = " + price);
//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);


        // 수정코드에 대한 테스트
        int userAPrice = statefulService1.order("userA", 10000);
        int userBPrice = statefulService2.order("userB", 20000);

        System.out.println("userAPrice = " + userAPrice);
        System.out.println("userBPrice = " + userBPrice);

        Assertions.assertThat(userAPrice).isEqualTo(10000);
    }

    static class StatefulServiceTestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}