package com.likelion.checkmate.follow.application;


import com.likelion.checkmate.follow.domain.entity.Follow;
import com.likelion.checkmate.follow.domain.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    @Transactional
    public void deleteFollow (Long followId, Long userId) {
        Follow follow = followRepository.findByIdAndUserId(followId, userId);
        followRepository.deleteById(followId);
    }


}
