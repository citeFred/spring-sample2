package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
    // 해당 설정 클래스에서는 구현체의 역할 들을 정확하게 알수 없는 문제점이 있다.
    // 다음처럼 수정해본다.

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    //memberService에서는 memberRepository를 사용한다고 이해 가능

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    //memberRepository는 MemoryMemberRepository를 사용한다고 이해 가능

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    //orderService는 memberRepository와 discountPolicy를 사용한다고 이해 가능

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
    //discountPolicy는 FixDiscountPolicy를 사용한다고 이해 가능.

    //다음 처럼 전체 앱 구조를 이해하기 편해진다.
}
