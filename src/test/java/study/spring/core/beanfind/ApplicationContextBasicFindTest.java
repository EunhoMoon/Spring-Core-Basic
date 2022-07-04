package study.spring.core.beanfind;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import study.spring.core.AppConfig;
import study.spring.core.member.MemberService;
import study.spring.core.member.MemberServiceImpl;

class ApplicationContextBasicFindTest {

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

	@Test
	@DisplayName("빈 이름으로 조회")
	void findBeanByName() {
		MemberService memberService = ac.getBean("memberService", MemberService.class);
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}

	@Test
	@DisplayName("이름 없이 타입으로만 조회")
	void findBeanByType() {
		MemberService memberService = ac.getBean(MemberService.class);
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}

	@Test
	@DisplayName("구체 타입(반환 타입)으로 조회")
	void findBeanByName2() {
		// 역할이 아닌 구현에 의존하였기에 권장되는 좋은 코드는 아니
		MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}

	@Test
	@DisplayName("빈 이름으로 조회X")
	void findBeanByNameX() {
//		MemberService memberService = ac.getBean("xxxxx", MemberService.class);
		assertThrows(NoSuchBeanDefinitionException.class, 
				() -> ac.getBean("xxxxx", MemberService.class));
	}
}
