package com.kh.finalPrjAm.repository;

import com.kh.finalPrjAm.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);     // NullPointException을 방지해주는 Optional wrap 클래스
    boolean existsByEmail(String email);

}
