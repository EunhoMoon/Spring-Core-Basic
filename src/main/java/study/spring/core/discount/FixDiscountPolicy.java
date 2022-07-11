package study.spring.core.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import study.spring.core.member.Grade;
import study.spring.core.member.Member;

@Component
//@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy {
	
	private int dicountFixAmount = 1000;	// 1000원 할인

	@Override
	public int discount(Member member, int price) {
		if (member.getGrade() == Grade.VIP) {
			return dicountFixAmount;
		} else {
			return 0;
		}
	}

}
