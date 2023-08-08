package com.likelion.checkmate.follow.presentation.response;

import com.likelion.checkmate.follow.application.dto.FollowDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowResponse {
    List<FollowDto> following;
    List<FollowDto> follower;

    public static FollowResponse toResponse(List<FollowDto> following, List<FollowDto> follower) {
        return FollowResponse.builder()
                .following(following)
                .follower(follower)
                .build();
    }
}
