package com.likelion.checkmate.post.application.dto;

import com.likelion.checkmate.follow.application.dto.FollowDeleteDto;
import com.likelion.checkmate.post.presentation.request.PostDeleteRequest;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDeleteDto {
    private Long userId;
    private Long postId;
    public static PostDeleteDto toDto(PostDeleteRequest request) {
        return PostDeleteDto.builder()
                .userId(request.getUserId())
                .postId(request.getPostId())
                .build();
    }
}
