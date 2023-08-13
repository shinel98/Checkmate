package com.likelion.checkmate.jwt;



import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


@ConfigurationProperties(prefix = "custom.jwt")
public class JwtProperties {
    private final SecretKey key;
    private final String issuer;
    private final String accessTokenExpiry;
    private final String refreshTokenExpiry;

    //    public void setSecret(String secret) {
//        byte[] decoded = Base64.getDecoder().decode(secret);
//        this.key = new SecretKeySpec(decoded, "HmacSHA256");
//    }
    @ConstructorBinding
    public JwtProperties(String secret, String issuer, String accessTokenExpiry, String refreshTokenExpiry) {
        this.issuer = issuer;
        this.accessTokenExpiry = accessTokenExpiry;
        this.refreshTokenExpiry = refreshTokenExpiry;

        byte[] decoded = Base64.getDecoder().decode(secret);
        this.key = new SecretKeySpec(decoded, "HmacSHA256");
    }

    public String getTokenExpiry(GrantType type) {
        return (type.equals(GrantType.ACCESS_TOKEN) ? accessTokenExpiry : refreshTokenExpiry);
    }
    public SecretKey getKey() {
        return key;
    }

    public String getIssuer() {
        return issuer;
    }
}

