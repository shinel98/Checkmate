package com.likelion.checkmate.follow.application;


import com.likelion.checkmate.follow.application.dto.FollowDeleteDto;
import com.likelion.checkmate.follow.application.dto.FollowDto;
import com.likelion.checkmate.follow.domain.entity.Follow;
import com.likelion.checkmate.follow.domain.repository.FollowRepository;
import com.likelion.checkmate.follow.presentation.response.FollowResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    @Transactional
    public void deleteFollow (FollowDeleteDto dto) {
        followRepository.deleteByFollowerIdAndFollowingId(dto.getUserId(), dto.getFollowingId());
    }

    @Transactional
    public FollowResponse readAll (Long userId) {

        List<Follow> followerList = followRepository.findAllByFollowingId(userId);
        List<Follow> followingList = followRepository.findAllByFollowerId(userId);

        System.out.println("followingList = " + followingList);
        System.out.println("followerList = " + followerList);
        List<FollowDto> followerDtoList= followerList.stream().
                map(follow -> FollowDto.toFollowerDto(follow))
                .collect(Collectors.toList());

        List<FollowDto> followingDtoList= followingList.stream()
                .map(follow -> FollowDto.toFollowingDto(follow))
                .collect(Collectors.toList());

        return FollowResponse.toResponse(followingDtoList, followerDtoList);

    }

}
