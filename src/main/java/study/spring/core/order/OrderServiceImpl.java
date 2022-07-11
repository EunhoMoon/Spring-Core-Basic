package study.spring.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import study.spring.core.discount.DiscountPolicy;
import study.spring.core.member.Member;
import study.spring.core.member.MemberRepository;

@Component
//@RequiredArgsConstructor	// final이 붙은 필수값을 지닌 생성자를 만들어준다.(필드 수정/추가에도 용이)
public class OrderServiceImpl implements OrderService {

	private final MemberRepository memberRepository;
	private final DiscountPolicy discountPolicy;

	@Autowired
	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//		public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
//		 @Qualifier : 특정 빈에 이름을 지정하고(빈의 이름이 변경되는 것은 아님), 지정된 빈을 사용할 수 있다.
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}

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
