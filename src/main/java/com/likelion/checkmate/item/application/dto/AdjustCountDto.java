package com.likelion.checkmate.item.application.dto;

import com.likelion.checkmate.item.presentation.request.AdjustCountRequest;
import com.likelion.checkmate.post.application.dto.PostHomeDto;
import com.likelion.checkmate.post.domain.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdjustCountDto {
    private Long itemId;
    private Long postId;
    private List<Long> usercheck;
    public static AdjustCountDto toDto(AdjustCountRequest request) {
        return AdjustCountDto.builder()
                .itemId(request.getItemId())
                .postId(request.getPostId())
                .usercheck(request.getUserCheck())
                .build();
    }

}
