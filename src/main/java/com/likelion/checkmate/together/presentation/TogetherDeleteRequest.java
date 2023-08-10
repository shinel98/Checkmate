package com.likelion.checkmate.together.presentation;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TogetherDeleteRequest {
    private Long userId;
    private Long postId;
}
