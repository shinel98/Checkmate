package com.likelion.checkmate.post.domain.repository;


import com.likelion.checkmate.post.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByIdAndUserId(Long postId, Long userId);

    @Query("SELECT p FROM Post p WHERE p.scope = 3 ORDER BY p.uploadDate DESC")
    List<Post> findAllOrderByRegDateDesc();

    @Query("SELECT p " +
            "FROM Post p " +
            "LEFT JOIN Have h ON p.id = h.post.id " +
            "GROUP BY p.id, p.title, p.scope " +
            "ORDER BY COUNT(h.post.id) DESC, p.id ASC")
    List<Post> findPostsByMostCountedHave();

    @Query("SELECT p " +
            "FROM Post p " +
            "LEFT JOIN Together t ON p.id = t.post.id " +
            "GROUP BY p.id, p.title, p.scope " +
            "ORDER BY COUNT(t.post.id) DESC, p.id ASC")
    List<Post> findPostsByMostCountedTogether();


}
