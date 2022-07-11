package study.spring.core.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import study.spring.core.annotation.MainDiscountPolicy;
import study.spring.core.member.Grade;
import study.spring.core.member.Member;

@Component
// @Qualifier("mainDiscountPolicy") -> 문자이기 때문에 컴파일에서 오류를 잡을 수 없다는 단점이 존재 
// @Primary
// @Primary : 같은 타입의 빈에 우선순위를 부여
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

	private int discountPercent = 10;

	@Override
	public int discount(Member member, int price) {
		if (member.getGrade() == Grade.VIP) {
			return price * discountPercent / 100;
		} else {
			return 0;
		}
	}

}
