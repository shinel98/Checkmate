package com.likelion.checkmate.item.presentation.controller;

import com.likelion.checkmate.item.application.service.ItemService;
import com.likelion.checkmate.item.presentation.request.IncreaseCountRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
