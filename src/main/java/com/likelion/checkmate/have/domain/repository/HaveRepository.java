package com.likelion.checkmate.have.domain.repository;

import com.likelion.checkmate.have.domain.entity.Have;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HaveRepository extends JpaRepository<Have, Long> {
    @Query("SELECT COUNT(h) FROM Have h WHERE h.post.id = :postId AND h.deleted = false ")
    int findAllGetPost(Long postId);
}
