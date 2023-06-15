package com.kh.finalPrjAm.entity;
import com.kh.finalPrjAm.constant.Authority;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // JPA에 Entity클래스 임을 알려줌, DB Table로 만들어져야 할 클래스
@Table(name = "member") // 생성 될 DB 이름을 정해줌 (자바의 표기법은 대소문자를 구분하며 카멜표기법을 따름)
@Getter @Setter @ToString
@NoArgsConstructor
public class Member {
    @Id // 해당 필드가 primary key임을 지정
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userId;
    private String name;
    private String password;
    @Column(unique = true)
    private String email;
    private LocalDateTime joinTime;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Member(String user, String email, String password, String name, Authority authority) {
        this.userId = user;
        this.email = email;
        this.password = password;
        this.name = name;
        this.authority = authority;
    }
}