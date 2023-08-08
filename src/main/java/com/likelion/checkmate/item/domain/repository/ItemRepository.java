package com.likelion.checkmate.item.domain.repository;

import com.likelion.checkmate.item.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByPostId(Long postId);

    void deleteAllByPostId(Long postId);
}
