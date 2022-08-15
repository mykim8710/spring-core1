package com.example.springcore1.componentscan.filter;

import java.lang.annotation.*;

// 이 애노테이션이 붙으면 component scan에서 포함

@Target(ElementType.TYPE)   // class level
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
