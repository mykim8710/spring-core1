package com.example.springcore1.member;

import java.util.HashMap;
import java.util.Map;


// 데이터베이스가 아직 확정이 안되었다. 그래도 개발은 진행해야 하니 가장 단순한, 메모리 회원 저장소를 구현해서 우선 개발을 진행
public class MemoryMemberRepository implements MemberRepository {

    // HashMap은 동시성 이슈가 발생할 수 있다. 이런 경우 ConcurrentHashMap을 사용
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
