package study.spring.core.autowired;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import study.spring.core.member.Member;

public class AutowiredTest {

	@Test
	void AutowiredOption() {
		// 옵션 처리
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
	}
	
	static class TestBean {
		// Member는 스프링에 등록된 Bean이 아님 -> Null
		
		@Autowired(required = false)	// required : 기본값 true
		public void setNoBean1(Member noBean1) {
			System.out.println("noBean1 = " + noBean1);
			// 의존관계가 없을 경우(자동주입할 대상 X) 메소드 자체가 호출되지 않는다.
		}
		
		@Autowired
		public void setNoBean2(@Nullable Member noBean2) {
			System.out.println("noBean2 = " + noBean2);
			// 호출은 되지만, Null로 들어온다.
		}
		
		@Autowired
		public void setNoBean3(Optional<Member> noBean3) {
			System.out.println("noBean3 = " + noBean3);
			// 호출이 되고, Null일 경우 Optional.empty 반환
		}
		
	}
	
}
