package com.kh.finalPrjAm.service;

import com.kh.finalPrjAm.constant.ItemSellStatus;
import com.kh.finalPrjAm.dto.ItemDto;
import com.kh.finalPrjAm.entity.Item;
import com.kh.finalPrjAm.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ItemService {
    // 의존성 통해 빈에 등록된 필드는 불변성이 있으야 하므로 final 선언을 해야 함
    private final ItemRepository itemRepository;
    // 상품 생성
    public boolean createItem(String name, int price, String detail,
                              ItemSellStatus status, int stock) {
        Item item = new Item();
        item.setItemNm(name);
        item.setPrice(price);
        item.setItemDetail(detail);
        item.setItemSellStatus(status);
        item.setStockNumber(stock);
        Item saveItem = itemRepository.save(item);
        log.info("저장된 상품 이름 : " + saveItem.getItemNm());
        return true;
    }

    // 상품 조회
    public List<ItemDto> getItemList() {
        List<Item> items = itemRepository.findAll();
        List<ItemDto> itemDtos = new ArrayList<>();
        for (Item item : items) {
            ItemDto itemDto = new ItemDto();
            itemDto.setItemNm(item.getItemNm());
            itemDto.setPrice(item.getPrice());
            itemDto.setItemDetail(item.getItemDetail());
            itemDtos.add(itemDto);
        }
        return itemDtos;
    }

}
