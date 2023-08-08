package com.likelion.checkmate.hashtag.domain.repository;

import com.likelion.checkmate.hashtag.domain.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    List<Hashtag> findAllByPostId(Long postId);

    void deleteAllByPostId(Long postId);


}
