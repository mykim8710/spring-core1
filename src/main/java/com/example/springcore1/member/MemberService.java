package com.example.springcore1.member;

public interface MemberService {
    // 회원가입
    void joinMember(Member member);

    // 회원조회
    Member findMember(Long memberId);
}
