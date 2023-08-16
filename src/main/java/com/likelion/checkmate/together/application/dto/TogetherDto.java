package com.likelion.checkmate.together.application.dto;

import com.likelion.checkmate.together.presentation.request.TogetherRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TogetherDto {
    private Long userId;
    private Long postId;

    public static TogetherDto toDto(TogetherRequest request) {
        return TogetherDto.builder()
                .userId(request.getUserId())
                .postId(request.getPostId())
                .build();

    }
}
