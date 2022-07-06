package study.spring.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "study.spring.core", basePackageClasses = AutoAppConfig.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
// ComponentScan을 통해 자동으로 @Componet가 붙은 대상을 Bean으로 등록 
// basePackages : 탐색을 package의 시작 위치를 지정할 수 있다.(탐색 효율 향상 -> 지정하지 않으면 외부 라이브러리까지 포함되기 때문에 낭비가 심하다.)
// basePackages : ','를 사용하여 여러 개의 시작위치 지정 가능
// basePackageClasses : 지정된 클래스의 패키지를 시작위치로 지정, default = ComponentScan이 붙은 설정정보 Class로 지정된다.
// excludeFilters : 자동 등록에서 제외할 부분 지정(@Configuration은 @Component 어노테이션이 포함되어있다.(@Controller, @Service, @Repository 또한 동일))
//	<-> includeFilters : 자동 등록에서 추가할 부분 지정
//	-> 관례상 패키지 위치를 지정하지 않고, 설정 정보 Class의 위치를 프로젝트 최상단에 둔다.(불필요한 부분을 excludeFilters로 제외하면서 사용)
public class AutoAppConfig {
}