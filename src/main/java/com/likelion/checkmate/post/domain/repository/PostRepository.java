package com.likelion.checkmate.post.domain.repository;


import com.likelion.checkmate.post.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByIdAndUserId(Long postId, Long userId);

    @Query("SELECT p FROM Post p WHERE p.scope = 3 ORDER BY p.uploadDate DESC")
    List<Post> findAllOrderByRegDateDesc();

//    @Query("SELECT p FROM Post p WHERE p.scope = 3 ORDER BY p. DESC")
//    List<Post> findAllOrderByRegDateDesc();

//    @Query("SELECT COUNT(p) FROM Post p WHERE p.id = :postId AND p.deleted = false ")
//    int findAllGetPost(Long postId);



}
