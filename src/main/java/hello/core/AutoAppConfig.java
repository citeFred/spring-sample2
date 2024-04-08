package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
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
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    } // 수동 빈 정의와 자동 빈 정의 부분의 충돌 확인을 위한 코드 (이 경우 수동 등록 빈이 오버라이딩으로 우선권을 가짐)
}
