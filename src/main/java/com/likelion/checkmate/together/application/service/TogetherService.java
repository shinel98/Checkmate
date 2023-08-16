package com.likelion.checkmate.together.application.service;


import com.likelion.checkmate.post.domain.entity.Post;
import com.likelion.checkmate.post.domain.repository.PostRepository;

import com.likelion.checkmate.together.application.dto.TogetherDto;
import com.likelion.checkmate.together.domain.entity.Together;
import com.likelion.checkmate.together.domain.repository.TogetherRepository;
import com.likelion.checkmate.user.domain.entity.User;
import com.likelion.checkmate.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TogetherService {

    private final TogetherRepository togetherRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    @Transactional
    public void deleteTogether (TogetherDto dto) {
        togetherRepository.findByIdAndUserId(dto.getPostId(), dto.getUserId());
        togetherRepository.deleteById(dto.getPostId());
    }

    @Transactional
    public void createTogether (TogetherDto dto) {
        Together together = new Together();
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new IllegalArgumentException("no such user"));
        Post post = postRepository.findById(dto.getPostId()).orElseThrow(()-> new IllegalArgumentException("no such post"));
        togetherRepository.save(together.toEntity(post, user));
    }
}
