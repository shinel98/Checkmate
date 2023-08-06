package com.likelion.checkmate.post.domain.repository;


import com.likelion.checkmate.post.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByIdAndUserId(Long postId, Long userId);
}
