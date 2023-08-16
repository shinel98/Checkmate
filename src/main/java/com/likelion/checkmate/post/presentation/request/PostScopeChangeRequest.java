package com.likelion.checkmate.post.presentation.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostScopeChangeRequest {
    private Long userId;
    private Long postId;
    private int scope;
}
