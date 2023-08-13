package com.likelion.checkmate.post.domain.repository;


import com.likelion.checkmate.post.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByIdAndUserId(Long postId, Long userId);


    @Query("SELECT p FROM Post p WHERE p.scope = 3 ORDER BY p.uploadDate DESC")
    List<Post> findAllOrderByUploadDateDesc();

    @Query("SELECT p FROM Post p WHERE p.scope = 1 and p.user.id = :userId ORDER BY p.modifiedDate DESC")
    List<Post> findMyListOrderByModDateDesc(Long userId);

    @Query("SELECT p FROM Post p JOIN Together t ON t.user.id = :userId WHERE p.scope = 3 ORDER BY p.uploadDate DESC")
    List<Post> findTogetherListOrderByUploadDateDesc(Long userId);

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

    @Query("SELECT p " +
            "FROM Post p " +
            "LEFT JOIN Hashtag h ON p.id = h.post.id " +
            "WHERE p.title LIKE %:keyword% " +
            "OR h.name LIKE %:keyword% " +
            "AND p.scope = 3")
    List<Post> findPostsByTitleOrHashTag(String keyword);


}
