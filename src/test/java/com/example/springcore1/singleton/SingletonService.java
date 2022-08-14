package com.example.springcore1.singleton;

public class SingletonService {
    // 1. static 영역에 객체를 딱 1개만 생성
    private static SingletonService instance = new SingletonService();
    // Java(JVM)가 뜰때 바로 SingletonService의 static영역에 new SingletonService()를 실행하여 객체를 생성하여 instance에 참조를 넣어둠

    // 2. public으로 열어서 객체 인스터스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletonService() {}

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
