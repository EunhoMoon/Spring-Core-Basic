package study.spring.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

	@Test
	void prototypeBeanFind() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

		System.out.println("=====find prototypeBean1=====");
		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
		System.out.println("=====find prototypeBean2=====");
		PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
		System.out.println("prototypeBean1 = " + prototypeBean1);
		System.out.println("prototypeBean2 = " + prototypeBean2);

		assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

		prototypeBean1.destroy();
		prototypeBean2.destroy();
		// @Scope("prototype")일 경우 PreDestroy가 지원되지 않기 때문에(생성만 하고 관리하지 않음) 필요시 직접 종료 메서드를 호출해주어야 한다.
		ac.close();
	}

	@Scope("prototype")
	static class PrototypeBean {

		@PostConstruct
		public void init() {
			System.out.println("SingletonBean.init()");
		}

		@PreDestroy
		public void destroy() {
			System.out.println("SingletonBean.destroy()");
		}

	}
}
