package com.likelion.checkmate.item.application.service;

import com.likelion.checkmate.item.application.dto.AdjustCountDto;
import com.likelion.checkmate.item.domain.entity.Item;
import com.likelion.checkmate.item.domain.repository.ItemRepository;
import com.likelion.checkmate.user.domain.repository.UserRepository;
import com.likelion.checkmate.usercheck.domain.entity.Usercheck;
import com.likelion.checkmate.usercheck.presentation.controller.UsercheckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final UsercheckRepository usercheckRepository;

    @Transactional
    public void adjustCount(AdjustCountDto dto) {
        Item item = itemRepository.findByIdAndPostId(dto.getItemId(), dto.getPostId());
        List<Usercheck> usercheckList = usercheckRepository.findByPostIdAndItemId(dto.getPostId(), item.getId());
        if(!usercheckList.isEmpty()) {
            usercheckRepository.deleteAllByPostIdAndItemId(dto.getPostId(), item.getId());
        }
        dto.getUsercheck().stream()
                .map(userId -> {
                  Usercheck usercheck = new Usercheck();
                  usercheck.setUser(userRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("no such user")));
                  usercheck.setItem(item);
                  usercheck.setPostId(dto.getPostId());
                  return usercheck;
                })
                .forEach(usercheckRepository::save);

//        item.setCount(item.getCount() + 1); //카운트 증가
//        itemRepository.save(item);
    }
//    @Transactional
//    public void decreaseCount(AdjustCountDto dto) {
//        Item item = itemRepository.findById(itemId)
//                .orElseThrow(() -> new IllegalArgumentException("item not found"));
//        item.setCount(item.getCount() - 1); //카운트 감소
//        itemRepository.save(item);
//    }

}
