package com.example.springcore1.member;

public interface MemberRepository {
    void save(Member member);
    Member findById(Long id);
}
