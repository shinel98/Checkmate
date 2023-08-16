package com.likelion.checkmate.have.presentation.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HaveRequest {
    private Long userId;
    private Long postId;
}
