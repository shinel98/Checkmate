package com.likelion.checkmate.together.application.dto;

import com.likelion.checkmate.together.presentation.TogetherDeleteRequest;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TogetherDeleteDto {
    private Long userId;
    private Long postId;
    public static TogetherDeleteDto toDto(TogetherDeleteRequest request) {
        return TogetherDeleteDto.builder()
                .userId(request.getUserId())
                .postId(request.getPostId())
                .build();
    }
}
