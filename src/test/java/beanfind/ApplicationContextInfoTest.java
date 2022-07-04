package beanfind;

import java.util.Iterator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import study.spring.core.AppConfig;

class ApplicationContextInfoTest {
	
	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
	
	@Test
	@DisplayName("모든 빈 출력하기")
	void findAllBean() {
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			Object bean = ac.getBean(beanDefinitionName);
			System.out.println("name : " + beanDefinitionName + " / object : " + bean);
		}
	}
	
	@Test
	@DisplayName("애플리케이션 빈 출력하기")
	void findApplicationBean() {
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
			// BeanDefinition : Bean에 대한 메타데이터 정보
			
			if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
				// BeanDefinition.ROLE_APPLICATION : 애플리케이션을 개발하기 위해 등록된 Bean(직접 개발 or 외부 라이브러리)
				// BeanDefinition.ROLE_INFRASTRUCTURE : 스프링 컨테이너 내부에서 사용되는 빈
				Object bean = ac.getBean(beanDefinitionName);
				System.out.println("name : " + beanDefinitionName + " / object : " + bean);
			}
		}
	}

}
