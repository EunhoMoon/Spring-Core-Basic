package study.spring.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import lombok.RequiredArgsConstructor;

public class SingletonWithPrototypeTest1 {
	
	@Test
	void prototypeFind() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
		prototypeBean1.addCount();
		
		assertThat(prototypeBean1.getCount()).isEqualTo(1);
		
		PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
		prototypeBean2.addCount();
		
		assertThat(prototypeBean2.getCount()).isEqualTo(1);
	}
	
	@Test
	void singletonClientUsePrototype() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
		ClientBean clientBean1 = ac.getBean(ClientBean.class);
		int count1 = clientBean1.logic();
		
		assertThat(count1).isEqualTo(1);
		
		ClientBean clientBean2 = ac.getBean(ClientBean.class);
		int count2 = clientBean2.logic();
		
		assertThat(count2).isEqualTo(1);
	}
	
	@Scope("singleton")
//	@RequiredArgsConstructor
	static class ClientBean {
//		private final PrototypeBean prototypeBean;	// 생성시점에 주입 -> singleton이 되어버림
		
		@Autowired
//		private ObjectProvider<PrototypeBean> prototypeBeanProvider;
		/*
		 * ObjectProvider
		 *  - getObject() 메서드가 호출되어야 해당 타입의 Object를 찾아 반환해주는 기능 제공
		 *  - DL(의존성 탐색) 기능 정도만 제공하기 때문에 스프링에 대한 의존도가 낮다.(단위 테스트/mock코드 생성에 유리)
		 *  - 항상 새로운 객체를 생성하여 반환해준다.
		 */
		private Provider<PrototypeBean> prototypeBeanProvider;
		/*
		 * javax.inject.Provider
		 *  - 자바 표준, 기능이 단순, 단위 테스트/mock코드 생성에 유리(get() 메서드 하나만 보유)
		 *  - 별도의 라이브러리 필요
		 *  - 자바 표준이므로 스프링이 아닌 다른 컨테이너에서도 사용 가능
		 */
		
//		@Autowired
//		ApplicationContext applicationContext;
		
		public int logic() {
//			PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class); -> 번거롭고, 스프링에 너무 의존적인 코드
//			PrototypeBean prototypeBean = prototypeBeanProvider.getObject(); -> 아직 스프링을 의존한다는 것은 변하지 않음
			PrototypeBean prototypeBean = prototypeBeanProvider.get();
			prototypeBean.addCount();
			return prototypeBean.getCount();
		}
	}
	
	@Scope("prototype")
	static class PrototypeBean {
		private int count = 0;
		
		public void addCount() {
			count++;
		}
		
		public int getCount() {
			return count;
		}
		
		@PostConstruct
		public void init() {
			System.out.println("PrototypeBean.init() = " + this);
		}
		
		@PreDestroy
		public void destroy() {
			System.out.println("PrototypeBean.destroy()");
		}
	}

}
