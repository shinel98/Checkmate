package com.likelion.checkmate.subtopic.domain.repository;

import com.likelion.checkmate.subtopic.domain.entity.Subtopic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubtopicRepository extends JpaRepository<Subtopic, Long> {
    Subtopic findByItemId(Long itemId);

    void deleteAllByItemId(Long itemId);
}
