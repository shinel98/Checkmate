package com.likelion.checkmate.follow.domain.repository;

import com.likelion.checkmate.follow.domain.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    void deleteByFollowerIdAndFollowingId(Long userId, Long followId);

    List<Follow> findAllByFollowerId(Long userId);
    List<Follow> findAllByFollowingId(Long userId);



}
