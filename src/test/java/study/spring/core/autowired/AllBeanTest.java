package study.spring.core.autowired;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import study.spring.core.AutoAppConfig;
import study.spring.core.discount.DiscountPolicy;
import study.spring.core.member.Grade;
import study.spring.core.member.Member;

public class AllBeanTest {

	@Test
	void findAllBean() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
		
		DiscountService discountService = ac.getBean(DiscountService.class);
		Member member = new Member(1L, "userA", Grade.VIP);
		int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");
		
		assertThat(discountService).isInstanceOf(DiscountService.class);
		assertThat(discountPrice).isEqualTo(1000);
		
		int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");

		assertThat(rateDiscountPrice).isEqualTo(2000);
	}

	static class DiscountService {
		private final Map<String, DiscountPolicy> policyMap;
		private final List<DiscountPolicy> policies;

		@Autowired
		public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
			this.policyMap = policyMap;
			this.policies = policies;

			System.out.println("policyMap = " + policyMap);
			System.out.println("policyList = " + policies);
		}

		public int discount(Member member, int price, String discountCode) {
			DiscountPolicy discountPolicy = policyMap.get(discountCode);
			// 스프링에 등록된 빈을 Map으로 받아와 그 중 파라미터 값에 해당하는 빈을 리턴해준다.
			return discountPolicy.discount(member, price);
		}

	}

}
