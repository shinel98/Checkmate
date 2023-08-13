package com.likelion.checkmate.item.presentation.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncreaseCountRequest {
    private Long itemId;
}
