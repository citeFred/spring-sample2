package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);

        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    // 생성자 주입으로는 OrderServiceImpl을 생성할때부터 필요한 파라미터를 명시해야 하도록한다.(필수)
    // 생성자 주입 방식만이 필드에 초기화에 final(불변)을 넣을 수 있기 때문에 좋은 설계가 가능

}