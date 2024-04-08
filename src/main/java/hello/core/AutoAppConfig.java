package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core.member",
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
) // 어노테이션만 사용해도 되는데, 추가한 제외는 예시로 남겨둔 AppConfig.java 등 다른 설정을 스캔하지 않기 위해 추가작성한 부분임.(실제로는 이부분 불필요)
public class AutoAppConfig {
}
