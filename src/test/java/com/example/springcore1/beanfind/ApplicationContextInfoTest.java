package com.example.springcore1.beanfind;

import com.example.springcore1.config.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("스프링 컨테이너 등록된 모든 스프링 빈 출력하기.")
    void 스프링빈출력테스트() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName: beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName); // 빈 이름으로 빈 객체 조회
            System.out.println("beanName : " +beanDefinitionName +"  ||  object : " +bean);
        }
    }

    @Test
    @DisplayName("스프링 컨테이너 등록된 애플리케이션 스프링 빈 출력하기.")
    void 애플리케이션_스프링빈출력테스트() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName: beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);   // bean에 대한 메타데이터 정보

            // Role ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
            // Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("beanName : " +beanDefinitionName +"  ||  object : " +bean);
            }
        }
    }
}
