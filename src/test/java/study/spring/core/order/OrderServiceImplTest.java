package study.spring.core.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import study.spring.core.discount.FixDiscountPolicy;
import study.spring.core.member.Grade;
import study.spring.core.member.Member;
import study.spring.core.member.MemoryMemberRepository;

public class OrderServiceImplTest {

	@Test
	void creatOrder() {
		MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
		memoryMemberRepository.save(new Member(1L, "name", Grade.VIP));
		OrderServiceImpl orderServiceImpl = new OrderServiceImpl(memoryMemberRepository, new FixDiscountPolicy());
		/* 
		 * 생성자 주입 사용의 장점(불변, 누락)
		 * 	1) 컴파일 단계에서 오류 확인이 가능하여 처리에 용이하다
		 * 	2) private final 선언이 가능(private : 데이터 불변, final : 데이터 누락 확인 용이)
		 * 	3) 프레임워크에 의존하지 않고 순수 Java언어의 특징을 잘 살리는 방법
		 */
		Order createOrder = orderServiceImpl.createOrder(1L, "itemA", 10000);
		
		assertThat(createOrder.getDicountPrice()).isEqualTo(1000);
	}
	
}
