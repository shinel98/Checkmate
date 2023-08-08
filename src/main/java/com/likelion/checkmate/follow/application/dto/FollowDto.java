package com.likelion.checkmate.follow.application.dto;

import com.likelion.checkmate.follow.domain.entity.Follow;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowDto {
    private Long userId;
    private String nickname;

    public static FollowDto toFollowerDto(Follow follow) {
        return FollowDto.builder()
                .userId(follow.getFollower().getId())
                .nickname(follow.getFollower().getNickname())
                .build();
    }

    public static FollowDto toFollowingDto(Follow follow) {
        return FollowDto.builder()
                .userId(follow.getFollowing().getId())
                .nickname(follow.getFollowing().getNickname())
                .build();
    }

}
