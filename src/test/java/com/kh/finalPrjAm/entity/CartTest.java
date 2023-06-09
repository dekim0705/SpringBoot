package com.kh.finalPrjAm.entity;

import com.kh.finalPrjAm.repository.CartRepository;
import com.kh.finalPrjAm.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
// 스프링 컨텍스트를 로드 하여 테스트 환경을 설정
@SpringBootTest
@Transactional // 데이터베이스의 논리적인 작업 단위, 모두 성공하는 경우가 아니면 롤백 (테스트에서는 자동 롤백)
@Slf4j
@TestPropertySource(locations = "classpath:application-test.properties") // 테스트 코드 실행 시 해당 설정 파일을 우선적으로 불러옴
class CartTest {
    @Autowired // 스프링 컨테이너에서 해당 빈에 해당하는 의존성을 주입 받음
    CartRepository cartRepository;
    @Autowired
    MemberRepository memberRepository;
    @PersistenceContext // EntityManager를 사용하기 위한 어노테이션
    EntityManager em;

    // 회원 엔티티 생성
    public Member createMemberInfo() {
        Member member = new Member();
        member.setUserId("userId1");
        member.setPassword("userPwd1");
        member.setName("userName1");
        member.setEmail("email1@email.com");
        member.setJoinTime(LocalDateTime.now());
        return member; // 반환타입이 멤버 클래스 타입
    }
    @Test
    @DisplayName("장바구니 회원 매핑 조회 테스트")
    public void findCartAndMemberTest() {
        Member member = createMemberInfo();
        memberRepository.save(member);

        Cart cart = new Cart();
        cart.setMember(member);
        cart.setCartName("테스트용 장바구니");
        cartRepository.save(cart);

        em.flush(); // 실제 데이터베이스에 반영
        em.clear();

        Cart savedCart = cartRepository.findById(cart.getId()).orElseThrow(EntityNotFoundException::new); // 테스트 용도 : getId가 없다면 사용 가능한 에러 처리
        System.out.println("📌 회원 userID : " + member.getUserId());
        System.out.println("📌 장바구니 userID : " + savedCart.getMember().getUserId());
        assertEquals(savedCart.getMember().getUserId(), member.getUserId()); // 테스트 용도 : 값이 같은지 확인
    }
}