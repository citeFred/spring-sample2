package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        //given
        AppConfig appConfig = new AppConfig();

        //when
        //1. 조회 호출 할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        //2. 2번 클라이언트의 조회
        MemberService memberService2 = appConfig.memberService();


        //then
        //참조값이 다른것 == 새로운 객체가 생성된것을 확인
        System.out.println("memberService1 = " +memberService1); //MemberServiceImpl@3bf7ca37
        System.out.println("memberService2 = " +memberService2); //MemberServiceImpl@79079097
        //이처럼 수많은 조회 요청마다 객체가 생성/소멸이 반복되는 비효율 = 메모리 낭비가 심하다.
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체를 사용하는 경우")
    void singletonServiceTest() {
        //when
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        //then
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //when
        //1. 1번 클라이언트의 조회
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        //2. 2번 클라이언트의 조회
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //then
        //ac를 통해 조회한 memberService2개의 주소값 비교
        System.out.println("memberService1 = " +memberService1); //MemberServiceImpl@3bf7ca37
        System.out.println("memberService2 = " +memberService2); //MemberServiceImpl@79079097
        assertThat(memberService1).isSameAs(memberService2);
    }
}
