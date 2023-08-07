package com.likelion.checkmate.together.domain.application;


import com.likelion.checkmate.together.domain.domain.entity.Together;
import com.likelion.checkmate.together.domain.domain.repository.TogetherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TogetherService {

    private final TogetherRepository togetherRepository;
    @Transactional
    public void deleteTogether (Long postId, Long userId) {
        Together together = togetherRepository.findByIdAndUserId(postId, userId);
        togetherRepository.deleteById(together.getId());
    }

}
