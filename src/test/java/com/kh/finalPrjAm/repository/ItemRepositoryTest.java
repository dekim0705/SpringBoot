package com.kh.finalPrjAm.repository;

import com.kh.finalPrjAm.constant.ItemSellStatus;
import com.kh.finalPrjAm.entity.Item;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest // 단위 테스트 및 통합 테스트를 위해 스프링부트에서 제공하는 어놑테이션
@TestPropertySource(locations = "classpath:application-test.properties") // 테스트 코드 실행 시 해당 설정 파일을 우선적으로 불러옴
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest() {
        for(int i = 1; i <= 10; i++) {
            Item item = new Item();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000 + i*10);
            item.setItemDetail("테스트 상품의 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item);
        }
//        Item saveItem = itemRepository.save(item);
//        System.out.println(saveItem);
//        log.info(String.valueOf(saveItem));
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNmTest() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품5");
        for(Item e : itemList) {
            System.out.println("결과 : " + e.toString());
        }
    }
    @Test
    @DisplayName("상품명 or 상품상세설명 테스트")
    public void findByItemOrItemDetailTest() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품5", "테스트 상품의 상세 설명7");
        for(Item e : itemList) {
            System.out.println("결과 : " + e.toString());
        }
    }
    @Test
    @DisplayName("입력 받은 가격보다 작은 상품 조회 테스트")
    public void findByPriceLessThanTest() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPriceLessThan(10055);
        for(Item e : itemList) {
            System.out.println("결과 : " + e.toString());
        }
    }
    @Test
    @DisplayName("가격 내림차순 조회 테스트")
    public void findByPriceLessThanOrderByPriceDesc() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10076);
        for(Item e : itemList) {
            System.out.println("결과 : " + e.toString());
        }
    }
}