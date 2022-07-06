package study.spring.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import study.spring.core.discount.DiscountPolicy;
import study.spring.core.discount.FixDiscountPolicy;
import study.spring.core.discount.RateDiscountPolicy;
import study.spring.core.member.MemberRepository;
import study.spring.core.member.MemberService;
import study.spring.core.member.MemberServiceImpl;
import study.spring.core.member.MemoryMemberRepository;
import study.spring.core.order.OrderService;
import study.spring.core.order.OrderServiceImpl;

@Configuration
public class AppConfig {
	
	// @Bean memberService() -> new MemoryMemberRepository()
	// @Bean orderService() -> new MemoryMemberRepository()

	@Bean
	public MemberService memberService() {
		System.out.println("call AppConfig.memberService()");
		return new MemberServiceImpl(memberRepository());
	}

	@Bean
	public OrderService orderService() {
		System.out.println("call AppConfig.orderService()");
		return new OrderServiceImpl(memberRepository(), discountPolicy());
	}
	
	@Bean
	MemberRepository memberRepository() {
		System.out.println("call AppConfig.memberRepository()");
		return new MemoryMemberRepository();
	}
	
	@Bean
	DiscountPolicy discountPolicy() {
		return new RateDiscountPolicy();
	}

}
