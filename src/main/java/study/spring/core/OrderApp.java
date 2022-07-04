package study.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import study.spring.core.member.Grade;
import study.spring.core.member.Member;
import study.spring.core.member.MemberService;
import study.spring.core.order.Order;
import study.spring.core.order.OrderService;

public class OrderApp {

	public static void main(String[] args) {
		
//		AppConfig appConfig = new AppConfig();
//		MemberService memberService = appConfig.memberService();
//		OrderService orderService = appConfig.orderService();
		
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		
		MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
		OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

		Long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP);
		memberService.join(member);

		Order order = orderService.createOrder(memberId, "itemA", 10000);

		System.out.println("order : " + order.toString());
		System.out.println("order.calculatePrice() : " + order.calculatePrice());

	}

}
