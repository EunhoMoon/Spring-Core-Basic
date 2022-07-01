package study.spring.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import study.spring.core.AppConfig;
import study.spring.core.member.Grade;
import study.spring.core.member.Member;
import study.spring.core.member.MemberService;

public class OrderServiceTest {

	MemberService memberService;
	OrderService orderService;

	@BeforeEach
	// Test가 실행될 때마다 새로 실행
	public void beforeEach() {
		AppConfig appConfig = new AppConfig();
		memberService = appConfig.memberService();
		orderService = appConfig.orderService();
	}
	
	@Test
	void createOrder() {
		// when
		Long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP);
		memberService.join(member);

		// given
		Order order = orderService.createOrder(member.getId(), "itemA", 10000);

		// then
		Assertions.assertThat(order.getDicountPrice()).isEqualTo(1000);
	}

}
