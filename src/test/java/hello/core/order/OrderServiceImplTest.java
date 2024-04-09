package hello.core.order;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.createOrder(1L, "itemA", 10000);
    }

    // 만약 수정자 주입으로 OrderServiceImpl 이 주입되는 상황이면 이와 같이 NullPointerException이 발생한다.
    // createOrder는         Member member = memberRepository.findById(memberId);
    //        int discountPrice = discountPolicy.discount(member, itemPrice);
    // 처럼 repository와 discount 의존관계가 들어가있는 로직이기 때문 이 부분이 없다고 나타나는 오류이다.

}