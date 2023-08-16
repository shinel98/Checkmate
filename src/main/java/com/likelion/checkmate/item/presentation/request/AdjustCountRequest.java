package com.likelion.checkmate.item.presentation.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdjustCountRequest {
    private Long itemId;

    private Long postId;

    private List<Long> userCheck;
}
