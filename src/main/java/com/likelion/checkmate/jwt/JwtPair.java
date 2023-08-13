package com.likelion.checkmate.jwt;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class JwtPair {
    private String accessToken;
    private String refreshToken;

    public JwtPair(List<String> tokens) {
        this.accessToken = tokens.get(0);
        this.refreshToken = tokens.get(1);
    }
}

