package com.likelion.checkmate.follow.application.dto;

import com.likelion.checkmate.follow.domain.entity.Follow;
import com.likelion.checkmate.follow.presentation.request.FollowRequest;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowDeleteDto {
    private Long userId;
    private Long followingId;

    public static FollowDeleteDto toDto(FollowRequest request) {
        return FollowDeleteDto.builder()
                .userId(request.getUserId())
                .followingId(request.getFollowingId())
                .build();
    }
}
