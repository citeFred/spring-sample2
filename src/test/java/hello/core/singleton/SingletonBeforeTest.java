package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonBeforeTest {

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
}
