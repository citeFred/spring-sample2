package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 기존 고정 할인 정책
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 변경하고자하는 비율 할인 정책

    /*
     * 기존 고정 할인 정책에서
     * 할인 정책을 변경하는데 이 부분 코드를 수정해야하는 문제점
     * [1] 역할과 구현을 분리했다 O
     * [2] OCP, DIP같은 객체지향 설계 원칙을 준수했다? X 그렇게 보이지만 아니다. 구현 클래스(new ...)를 직접 수정하고 있기 떄문
     *  - 정책을 변경하려고 서비스 계층(클라이언트)에서도 추상 클래스만이 아닌 구현 클래스(하위)에도 의존하고 있다. (DIP 위반:상위참조만하라)
     *  - 지금 코드는 기능을 확장해서 변경하면, 클라이언트 코드(서비스 계층) 코드에 영향을 준다. (OCP 위반:확장개방 수정폐쇄)
     * */


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
