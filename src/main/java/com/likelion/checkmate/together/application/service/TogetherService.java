package com.likelion.checkmate.together.application.service;


import com.likelion.checkmate.together.application.dto.TogetherDeleteDto;
import com.likelion.checkmate.together.domain.repository.TogetherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TogetherService {

    private final TogetherRepository togetherRepository;
    @Transactional
    public void deleteTogether (TogetherDeleteDto dto) {
        togetherRepository.findByIdAndUserId(dto.getPostId(), dto.getUserId());
        togetherRepository.deleteById(dto.getPostId());
    }

}
