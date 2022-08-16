package com.example.springcore1.beanfind;

import com.example.springcore1.discount.DiscountPolicy;
import com.example.springcore1.discount.FixDiscountPolicy;
import com.example.springcore1.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(BeanFindTestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다")
    void 부모타입으로_빈조회테스트() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈이름을 지정하면 된다.")
    void 부모타입으로_빈이름을지정_빈조회테스트() {
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void 특정하위타입으로_빈조회테스트() {
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void 부모타입으로_하위모든빈조회테스트() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        System.out.println("beansOfType = " + beansOfType);

        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key +"  |  value = " +beansOfType.get(key));
        }

        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    void object타입으로_모든빈조회테스트() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        System.out.println("beansOfType = " + beansOfType);

        int totalBeanCount = 0;
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key +"  |  value = " +beansOfType.get(key));
            totalBeanCount += 1;
        }

        System.out.println("beanCount = " + totalBeanCount);
        assertThat(beansOfType.size()).isEqualTo(totalBeanCount);
    }

    @Configuration
    static class BeanFindTestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixedDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}