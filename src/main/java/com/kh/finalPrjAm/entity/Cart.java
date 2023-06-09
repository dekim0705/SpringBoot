package com.kh.finalPrjAm.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
public class Cart {
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="cart_name")
    private String cartName;

    @OneToOne // ✨ 일대일 관계 매핑
    @JoinColumn(name="member_id") // 매핑할 외래키 지정
    private Member member; // 클래스를 줘야 함. 객체를 참조할 수 있는 참조변수가 있어야 함.


}
