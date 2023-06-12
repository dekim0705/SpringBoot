package com.kh.finalPrjAm.controller;

import com.kh.finalPrjAm.constant.ItemSellStatus;
import com.kh.finalPrjAm.dto.ItemDto;
import com.kh.finalPrjAm.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/item")
public class ItemController {
    ItemService itemService;
    public ItemController (ItemService itemService) { // 생성자를 통해서 의존성주입을 받는 방법
        this.itemService = itemService;
    }

    // 제품 등록
    @PostMapping("/new")
    public ResponseEntity<Boolean> itemCreate(@RequestBody Map<String, Object> data) {
        String name = (String)data.get("name");
        int price = (Integer)data.get("price");
        String detail = (String)data.get("detail");
        int stock = (Integer)data.get("stock");
        String status = (String)data.get("status");
        boolean result = itemService.createItem(name, price, detail, ItemSellStatus.SELL, stock);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    // 제품 조회
    @GetMapping("/get")
    public ResponseEntity<List<ItemDto>> itemList(@RequestParam String name) {
        List<ItemDto> list = itemService.getItemList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
