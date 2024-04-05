package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //when
        //ThreadA: A사용자 10000원 주문
        statefulService1.order("userA", 10000);
        //ThreadA: B사용자 20000원 주문
        statefulService2.order("userB", 20000);


        //ThreadA: 사용자A 주문 금액 조회 하는 사이에 위 B사용자가 주문하면서 20000으로 업데이트 되버림.
        // 같은 객체(service)를 바라보고 있어서 동일한 필드인 price에 할당해버림.
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        //then
        assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}