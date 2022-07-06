package study.spring.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import study.spring.core.AppConfig;
import study.spring.core.member.MemberRepository;
import study.spring.core.member.MemberServiceImpl;
import study.spring.core.order.OrderServiceImpl;

public class ConfigurationSingletonTest {

	@Test
	void configurationTest() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class); 
		
		MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
		OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
		MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
		
		MemberRepository memberRepository1 =  memberService.getMemberRepository();
		MemberRepository memberRepository2 = orderService.getMemberRepository();
		System.out.println("memberService -> memberRepository = " + memberRepository1);
		System.out.println("orderService -> memberRepository = " + memberRepository2);
		System.out.println("memberRepository = " + memberRepository);
		// 세 개의 Bean이 같은 값을 가지고 있다.(모두 같은 인스턴스를 공유)
		
		assertThat(memberRepository1).isSameAs(memberRepository2).isSameAs(memberRepository);
	}
	
	@Test
	void configurationDeep() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class); 
		
		AppConfig bean = ac.getBean(AppConfig.class);
		
		System.out.println("bean = " + bean.getClass());
		
	}
	
}
