package com.kh.finalPrjAm.repository;

import com.kh.finalPrjAm.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// 📌JpaRepository의 첫 번째에는 엔티티 타입 클래스를 넣어주고, 두 번째는 기본 키
//    기본적인 CRUD 및 페이징 처리를 위한 메소드는 정의 되어 있음.
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByItemNm(String itemNm);

    // OR 조건 처리하기
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    // LessThan : 매개변수로 전달 받은 값보다 작은 상품 조회
    List<Item> findByPriceLessThan(Integer price);

    // OrderBy로 정렬 : OrderBy + 속성명 + Asc or Desc
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    // Between : 범위에 해당하는 조건 검색
    List<Item> findByPriceBetween(Integer min, Integer max);

    // 전달된 부분 문자열에 대한 검색
    List<Item> findByItemNmContaining(String keyword);

    // JPQL (JPQ Query Language) : 조건이 복잡한 경우 쿼리 메소드를 선언하는 것이 너무 복잡하거나 만들 수 없는 경우에 사용
    // JPQL은 실제 쿼리와는 다르게 JPA 엔티티에 사용 됨
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

    // ❗️nativeQuery는 DB와 바로 연결되기 때문에 column이름을 맞춰줘야 함 + 해당 DB에 맞는 문법
    @Query(value="select * from item i where i.item_detail like %:itemDetail% order by i.price desc",
            nativeQuery = true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);

}
