package com.example.springcore1.member;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 회원가입 비지니스 로직
    @Override
    public void joinMember(Member member) {
        memberRepository.save(member);
    }

    // 회원조회 비지니스 로직
    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
