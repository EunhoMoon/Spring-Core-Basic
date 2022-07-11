package study.spring.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient {
	// implements InitializingBean, DisposableBean

	private String url;

	public NetworkClient() {
		System.out.println("생성자 호출, url = " + url);
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	// 서비스 시작시 호출
	public void connect() {
		System.out.println("connect: " + url);
	}
	
	public void call(String message) {
		System.out.println("call: " + url + " message = " + message);
	}
	
	// 서비스 종료시 호출
	public void disconnect() {
		System.out.println("close: " + url);
	}

//	@Override at InitializingBean
//	public void afterPropertiesSet() throws Exception {
//		// 의존관계 주입이 끝난 후 호출
//		System.out.println("NetworkClient.afterPropertiesSet()");
//		connect();
//		call("초기화 연결 메시지");		
//	}
//
//	@Override at DisposableBean
//	public void destroy() throws Exception {
//		// 빈이 종료될 때 호출
//		System.out.println("NetworkClient.destroy()");
//		disconnect();
//	}
	
	@PostConstruct
	public void init() throws Exception {
		// 의존관계 주입이 끝난 후 호출
		System.out.println("NetworkClient.init()");
		connect();
		call("초기화 연결 메시지");		
	}

	@PreDestroy
	public void close() throws Exception {
		// 빈이 종료될 때 호출
		System.out.println("NetworkClient.close()");
		disconnect();
	}
	
	/*
	 * @PostConstruct, @PreDestroy
	 *  - 최신 스프링에서 가장 권장하는 방법
	 *  - 애노테이션 하나만 붙이면 되기 때문에 사용이 매우 편리
	 *  - javax에서 제공하기 때문에 스프링이 아닌 타 컨테이너에서도 동작
	 *  - 외부 라이브러리에 적용하지 못한다는 단점이 존재(@Bean에서 initMethod, destroyMethod 사용)
	 */
}