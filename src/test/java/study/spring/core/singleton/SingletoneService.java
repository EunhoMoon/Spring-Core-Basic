package study.spring.core.singleton;

public class SingletoneService {

	private static final SingletoneService instance = new SingletoneService();
	// 자기 자신을 static으로 선언해 class 레벨(instance 영역)에서 하나만 존재하게 된다.(Java 실행시 미리 하나를 생성해서 올려둔다.)

	public static SingletoneService getInstance() {
		return instance;
	}

	private SingletoneService() {
		// 외부에서 생성자에 접근하는 것을 막기 위해 생성자를 private로 선언
		// 외부에서 new 키워드로 객체 인스턴스가 생성되는 것을 막는다.
	}
	
	public void logic() {
		System.out.println("싱글톤 객체 로직 호출");
	}

}
