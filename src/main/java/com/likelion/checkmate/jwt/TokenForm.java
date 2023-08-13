package com.likelion.checkmate.jwt;
import lombok.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenForm {

    private String grantType;

    private String refreshToken;
}
