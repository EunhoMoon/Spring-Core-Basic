package study.spring.core.discount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import study.spring.core.member.Grade;
import study.spring.core.member.Member;
import study.spring.core.order.RateDiscountPolicy;

import static org.assertj.core.api.Assertions.*;
// static import로 간결하게 사용하는 것이 좋다.

class RateDiscountPolicyTest {
	
	RateDiscountPolicy discountPolicy = new RateDiscountPolicy();
	
	@Test
	@DisplayName("VIP는 10% 할인이 되어야 한다.")
	void vip_o() {
		// given
		Member member = new Member(1L, "memberVIP", Grade.VIP);
		
		// when
		int discountPrice = discountPolicy.discount(member, 10000);
		
		// then
		assertThat(discountPrice).isEqualTo(1000);
	}

	@Test
	@DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
	void vip_x() {
		// given
		Member member = new Member(1L, "memberBASIC", Grade.BASIC);
		
		// when
		int discount = discountPolicy.discount(member, 10000);
		
		// then
		assertThat(discount).isEqualTo(0);
	}
}
