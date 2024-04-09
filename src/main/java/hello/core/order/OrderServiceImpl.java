package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    @Autowired private MemberRepository memberRepository;
    @Autowired private DiscountPolicy discountPolicy;

    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
    //수정자 주입(setter)
    //@Autowired(required = false)
    //public void setDiscountPolicy(DiscountPolicy discountPolicy) {
    //    this.discountPolicy = discountPolicy;
    //}
    //
    //@Autowired
    //public void setMemberRepository(MemberRepository memberRepository) {
    //    this.memberRepository = memberRepository;
    //}

    //@Autowired //생성자가 1개일땐 생략 가능
    //public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    //    this.memberRepository = memberRepository;
    //    this.discountPolicy = discountPolicy;
    //}

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //싱글톤 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
