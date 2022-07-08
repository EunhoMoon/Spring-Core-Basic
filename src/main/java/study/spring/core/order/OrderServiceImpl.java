package study.spring.core.order;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import study.spring.core.discount.DiscountPolicy;
import study.spring.core.member.Member;
import study.spring.core.member.MemberRepository;

@Component
@RequiredArgsConstructor	// final이 붙은 필수값을 지닌 생성자를 만들어준다.(필드 수정/추가에도 용이)
public class OrderServiceImpl implements OrderService {

	private final MemberRepository memberRepository;
	private final DiscountPolicy discountPolicy;

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);

		return new Order(memberId, itemName, itemPrice, discountPrice);
	}

	// 테스트 용도
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}

}
