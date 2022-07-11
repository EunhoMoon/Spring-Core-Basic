package study.spring.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

	@Test
	public void lifeCycleTest() {
		ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
		NetworkClient client = ac.getBean(NetworkClient.class);
		ac.close();
	}
	
	@Configuration
	static class LifeCycleConfig {
		
//		@Bean(initMethod = "init", destroyMethod = "close")
//		/* 
//		 * InitializingBean, DisposableBean 사용에 비해 
//		 * 	1) 스프링에 의존하지 않는다. 
//		 * 	2) 메서드 이름을 자유롭게 지정할 수 있다.
//		 * 	3) 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드를 사용할 수 있다.
//		 *  4) destroyMethod의 기본적으로 이름이 close나 shutdown인 메서드를 찾아 실행해준다.(default : (inferred))
//		 */
		@Bean
		public NetworkClient client() {
			NetworkClient client = new NetworkClient();
			client.setUrl("http://hello-spring.dev");
			return client;
		}
		
	}
	
}
