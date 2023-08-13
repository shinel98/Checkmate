package com.likelion.checkmate.item.presentation.controller;

import com.likelion.checkmate.item.application.service.ItemService;
import com.likelion.checkmate.item.presentation.request.DecreaseCountRequest;
import com.likelion.checkmate.item.presentation.request.IncreaseCountRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    @PostMapping("/item/increase-count")
    public ResponseEntity<Void> increaseCount(@RequestBody IncreaseCountRequest request) {
        itemService.increaseCount(request.getItemId());
        return ResponseEntity.ok().build();
    }

    @PatchMapping("item/decrease-count")
    public ResponseEntity<Void> decreaseCount(@RequestBody DecreaseCountRequest request) {
        itemService.decreaseCount(request.getItemId());
        return ResponseEntity.ok().build();
    }

}
