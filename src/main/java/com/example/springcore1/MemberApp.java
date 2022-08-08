package com.example.springcore1;

import com.example.springcore1.member.Grade;
import com.example.springcore1.member.Member;
import com.example.springcore1.member.MemberService;
import com.example.springcore1.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();

        // join Member Test
        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.joinMember(memberA);

        // find Member Test
        Member findMember = memberService.findMember(1L);
        System.out.println("new Member : " +memberA.getName());
        System.out.println("find Member : " +findMember.getName());
    }
}
