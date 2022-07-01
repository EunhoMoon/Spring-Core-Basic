package study.spring.core;

import study.spring.core.member.Grade;
import study.spring.core.member.Member;
import study.spring.core.member.MemberService;
import study.spring.core.member.MemberServiceImpl;

public class MemberApp {
	
	public static void main(String[] args) {
		
		MemberService memberService = new MemberServiceImpl();
		
		Member member = new Member(1L, "memberA", Grade.VIP);
		memberService.join(member);
		
		Member findMember = memberService.findMember(1L);
		
		System.out.println("new member : " + member.getName());
		System.out.println("findMember : " + findMember.getName());
	}

}
