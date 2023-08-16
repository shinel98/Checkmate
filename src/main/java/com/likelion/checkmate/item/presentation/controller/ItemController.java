package com.likelion.checkmate.item.presentation.controller;

import com.likelion.checkmate.item.application.dto.AdjustCountDto;
import com.likelion.checkmate.item.application.service.ItemService;
import com.likelion.checkmate.item.presentation.request.AdjustCountRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    @PatchMapping("/item/adjust-count")
    public ResponseEntity<Void> increaseCount(@RequestBody AdjustCountRequest request) {
        itemService.adjustCount(AdjustCountDto.toDto(request));
        return ResponseEntity.ok().build();
    }
//    @PatchMapping("/item/decrease-count")
//    public ResponseEntity<Void> decreaseCount(@RequestBody AdjustCountRequest request) {
//        itemService.decreaseCount(AdjustCountDto.toDto(request));
//        return ResponseEntity.ok().build();
//    }

}
