package com.likelion.checkmate.have.application.dto;

import com.likelion.checkmate.have.presentation.request.HaveRequest;
import com.likelion.checkmate.together.application.dto.TogetherDto;
import com.likelion.checkmate.together.presentation.request.TogetherRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HaveDto {
    private Long userId;
    private Long postId;

    public static HaveDto toDto(HaveRequest request) {
        return HaveDto.builder()
                .userId(request.getUserId())
                .postId(request.getPostId())
                .build();

    }
}
