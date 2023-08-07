package com.likelion.checkmate.together.domain.repository;
import com.likelion.checkmate.together.domain.entity.Together;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TogetherRepository extends JpaRepository<Together, Long> {
    Together findByIdAndUserId(Long postId, Long userId);
}
