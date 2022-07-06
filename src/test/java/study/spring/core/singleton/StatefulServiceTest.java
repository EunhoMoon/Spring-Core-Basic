package study.spring.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

	@Test
	void statefulServiceSingleton() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
		StatefulService statefulService1 = ac.getBean(StatefulService.class);
		StatefulService statefulService2 = ac.getBean(StatefulService.class);

//		// ThreadA : A사용자 10000원 주문
//		statefulService1.order("userA", 10000);
//		// ThreadB : B사용자 20000원 주문
//		statefulService2.order("userB", 20000);
//		
//		// ThreadA : A사용자 주문 금액 조회
//		int price = statefulService1.getPrice();

		// ThreadA : A사용자 10000원 주문
		int userAPrice = statefulService1.order("userA", 10000);
		// ThreadB : B사용자 20000원 주문
		int userBPrice = statefulService2.order("userB", 20000);

		// ThreadA : A사용자 주문 금액 조회
		System.out.println("price = " + userAPrice);

		assertThat(userAPrice).isNotEqualTo(userBPrice);
	}

	static class TestConfig {

		@Bean
		public StatefulService statefulService() {
			return new StatefulService();
		}

	}

}
