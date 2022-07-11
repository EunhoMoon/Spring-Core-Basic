package study.spring.core.common;

import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
/*
 * proxyMode : 가짜 프록시 클래스를 만들어 주입해준다.
 *  - 의존성이 주입될 때 해당 객체를 상속받은 가짜 프록시 객체를 주입
 * 	- 해당 객체를 상속받았기에 동일하게 사용 -> 다형성
 * 	- 실제 호출되는 시점에서 원본 객체를 찾아 응답해준다.
 * 	- 이를 통해 마치 싱글톤이 동작하는 것 처럼 작동
 *
 * 	- 실제 싱글톤은 아니기 때문에 주의해서 사용
 * 	- 무분별하게 사용시 유지보수 및 테스트가 어려워진다.
 */
public class MyLogger {

	private String uuid;
	private String requestURL;

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	public void log(String message) {
		System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + " [" + message + "]");
	}

	@PostConstruct
	public void init() {
		// Bean이 생성되는 시점에 초기화 메서드 호출
		uuid = UUID.randomUUID().toString();
		// HTTP 요청 당 하나씩 생성됨 = 다른 HTTP 요청과 구분 가능
		System.out.println("[" + uuid + "] request scope bean create:" + this);
		// 같은 요청에는 같은 uuid(spring bean)이 반환 된다.
	}

	@PreDestroy
	public void close() {
		System.out.println("[" + uuid + "] request scope bean close:" + this);
	}

}
