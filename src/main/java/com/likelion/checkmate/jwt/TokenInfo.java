package com.likelion.checkmate.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenInfo {
    private final String tokenType = "Bearer ";
    private GrantType grantType;
    private String token;
}