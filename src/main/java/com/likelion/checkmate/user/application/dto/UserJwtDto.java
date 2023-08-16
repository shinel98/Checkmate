package com.likelion.checkmate.user.application.dto;

import com.likelion.checkmate.jwt.JwtPair;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserJwtDto {

    private Boolean isRegistered;
    private String tokenType;
    private JwtPair tokens;
//    private String accessToken;

}
