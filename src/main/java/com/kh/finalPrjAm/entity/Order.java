package com.kh.finalPrjAm.entity;

import com.kh.finalPrjAm.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Getter @Setter @ToString
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;
    @ManyToOne // 한명의 회원은 여러 번 주문 가정
    @JoinColumn(name="member_id")
    private Member member;

    // 주문 상품 엔티티와 일대다 매핑
    // 외래키(order_id)가 order_item 테이블에 있으므로 연관 관계의 주인은 OrderItem 엔티티
    // Order 엔티티가 주인이 아니므로 "mappedBy" 속성으로 연관관계의 주인을 설정
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true) // mappedBy : 주인이 누군지?
    // CascadeType.All : 부모의 정보가 변경/삭제 될 때 자식에도 그대로 반영되는 것
    private List<OrderItem> orderItemList = new ArrayList<>();

    private LocalDateTime orderDate; // 주문일

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime regTime;
    private LocalDateTime updateTime;



}
