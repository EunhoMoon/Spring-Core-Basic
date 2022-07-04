package study.spring.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import study.spring.core.discount.DiscountPolicy;
import study.spring.core.discount.FixDiscountPolicy;
import study.spring.core.member.MemberRepository;
import study.spring.core.member.MemberService;
import study.spring.core.member.MemberServiceImpl;
import study.spring.core.member.MemoryMemberRepository;
import study.spring.core.order.OrderService;
import study.spring.core.order.OrderServiceImpl;
import study.spring.core.order.RateDiscountPolicy;

@Configuration	// 어플리케이션의 설정/구성 정보를 담당한다는 어노테이션
public class AppConfig {

	@Bean
	public MemberService memberService() {
		return new MemberServiceImpl(memberRepository());
	}

	@Bean
	public OrderService orderService() {
		return new OrderServiceImpl(memberRepository(), discountPolicy());
	}

	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}

	@Bean
	public DiscountPolicy discountPolicy() {
//		return new FixDiscountPolicy();
		return new RateDiscountPolicy();
	}

}
