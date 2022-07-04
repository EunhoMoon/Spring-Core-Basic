package beanfind;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import study.spring.core.discount.DiscountPolicy;
import study.spring.core.discount.FixDiscountPolicy;
import study.spring.core.order.RateDiscountPolicy;

public class ApplicationContextExtendsFindTest {

	ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

	@Test
	@DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다.")
	void findBeanByParentTypeDuplicate() {
		assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
	}

	@Test
	@DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
	void findBeanByParentTypeBeanName() {
		DiscountPolicy bean = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
		assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
	}
	
	@Test
	@DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 특정 하위 타입으로 조회하면 된다.")
	void findBeanBySubType() {
		DiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
		// 역할이 아닌 구현에 의존했기 때문에 좋은 코드는 아니다.
		assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
	}
	
	@Test
	@DisplayName("부모 타입으로 모두 조회하기")
	void findAllBeanByParentType() {
		Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
		assertThat(beansOfType.size()).isEqualTo(2);
		for (String key : beansOfType.keySet()) {
			System.out.println("key : " + key + " / class : " + beansOfType.get(key));
		}
	}
	
	@Test
	@DisplayName("부모 타입으로 모두 조회하기 - Object")
	void findAllBeanByObjectType() {
		Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
		for (String key : beansOfType.keySet()) {
			System.out.println("key : " + key + " / class : " + beansOfType.get(key));
		}
	}

	@Configuration
	static class TestConfig {

		@Bean
		DiscountPolicy rateDiscountPolicy() {
			return new RateDiscountPolicy();
		}

		@Bean
		DiscountPolicy fixDiscountPolicy() {
			return new FixDiscountPolicy();
		}
	}

}