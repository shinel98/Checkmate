package com.likelion.checkmate.item.application.service;

import com.likelion.checkmate.item.domain.entity.Item;
import com.likelion.checkmate.item.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void increaseCount(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("item not found"));
        item.setCount(item.getCount() + 1); //카운트 증가
        itemRepository.save(item);
    }

}
