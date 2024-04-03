package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    private DiscountPolicy discountPolicy;

    /*
     * 인터페이스만 의존하도록 변경(DIP, OCP 준수)
     * 하지만, 이 상태에서는 NullpointException 이 발생한다. Why?
     *  - 해당 변수에 아무 값(구현체)이 할당 되어있지 않기 때문.
     *
     * 누군가 discountPolicy에 구현체를 할당해줘야 한다.
     * */


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
