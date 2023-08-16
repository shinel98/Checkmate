package com.likelion.checkmate.usercheck.presentation.controller;

import com.likelion.checkmate.item.domain.entity.Item;
import com.likelion.checkmate.usercheck.domain.entity.Usercheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsercheckRepository extends JpaRepository<Usercheck, Long> {

    @Modifying
    @Query("DELETE FROM Usercheck u WHERE u.postId = :postId AND u.item.id = :itemId")
    void deleteAllByPostIdAndItemId(Long postId, Long itemId);

    List<Usercheck> findByPostIdAndItemId(Long postId, Long itemId);

    @Query("SELECT u.user.id FROM Usercheck u WHERE u.postId = :postId AND u.item.id = :itemId")
    List<Long> findAllUserCheckList(Long postId, Long itemId);
}
