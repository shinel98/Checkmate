package com.likelion.checkmate.follow.presentation.request;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowRequest {
    private Long userId;
    private Long followingId;
}
