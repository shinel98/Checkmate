package com.likelion.checkmate.user.presentation.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeNicknameRequest {
    private Long id;
    private String nickname;
}
