package com.likelion.checkmate.item.domain.repository;

import com.likelion.checkmate.item.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByPostId(Long postId);

    void deleteAllByPostId(Long postId);
    Optional<Item> findById(Long id);

    @Query("SELECT i FROM Item i  WHERE i.post.id = :postId AND i.id = :itemId")
    Item findByIdAndPostId(Long itemId, Long postId);
}
