package study.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import study.spring.core.member.Grade;
import study.spring.core.member.Member;
import study.spring.core.member.MemberService;

public class MemberApp {

	public static void main(String[] args) {

//		AppConfig appConfig = new AppConfig();
//		MemberService memberService = appConfig.memberService();

		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		// AppConfig의 정보를 스프링 빈에 등록하여 관리해준다.
		MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
		// parameter : 1) 메서드 이름(key) / 2) 반환 타입(value)

		Member member = new Member(1L, "memberA", Grade.VIP);
		memberService.join(member);

		Member findMember = memberService.findMember(1L);

		System.out.println("new member : " + member.getName());
		System.out.println("findMember : " + findMember.getName());
	}

}
