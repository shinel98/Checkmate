package com.likelion.checkmate.follow.domain.repository;

import com.likelion.checkmate.follow.domain.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Follow findByIdAndUserId(Long followId, Long userId);
}
