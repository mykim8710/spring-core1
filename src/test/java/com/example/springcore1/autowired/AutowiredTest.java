package com.example.springcore1.autowired;

import com.example.springcore1.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void autowired옵션테스트() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {
        //호출 안됨(자동주입할 대상이 없으면 메시드 자체가 호출이 안된다)
        @Autowired(required = false)
        public void setNoBean1(Member member) { // Member는 스프링 컨테이너가 관리하는 빈이 아니다
            System.out.println("setNoBean1 = " + member);
        }

        //null 호출
        @Autowired
        public void setNoBean2(@Nullable Member member) {
            System.out.println("setNoBean2 = " + member);
        }

        //Optional.empty 호출
        @Autowired(required = false)
        public void setNoBean3(Optional<Member> member) {
            System.out.println("setNoBean3 = " + member);
        }
    }
}
