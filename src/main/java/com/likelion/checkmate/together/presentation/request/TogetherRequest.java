package com.likelion.checkmate.together.presentation.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TogetherRequest {
    private Long userId;
    private Long postId;
}
