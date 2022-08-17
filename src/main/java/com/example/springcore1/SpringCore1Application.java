package com.example.springcore1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCore1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCore1Application.class, args);
	}
	// spring-boot-starter-web 라이브러리를 추가하면 스프링 부트는 내장 톰켓 서버를 활용해서 웹서버와 스프링을 함께 실행시킨다
	// 스프링 부트는 웹라이브러리가 없으면 AnnotationConfigApplicationContext 을 기반으로 애플리케이션을 구동한다.
	// 웹 라이브러리가 추가되면 웹과 관련된 추가 설정과 환경들이 필요하므로 AnnotationConfigServletWebServerApplicationContext를 기반으로 애플리케이션을 구동한다.
}
