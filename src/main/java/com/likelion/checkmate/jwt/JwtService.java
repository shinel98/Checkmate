package com.likelion.checkmate.jwt;


import com.likelion.checkmate.auth.Role;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {
    private final JwtProperties jwtProperties;

    public String issueToken(String email, String name, GrantType typ) {
        Map<String, Date> pair = calcExpiry(jwtProperties.getTokenExpiry(typ));

        return build(
                email, name,
                pair.get(Claims.ISSUED_AT),
                pair.get(Claims.EXPIRATION));
    }

    public JwtPair issueToken(String email, String name) {
        List<String> tokens = Arrays.stream(GrantType.values())
                .map(jwtProperties::getTokenExpiry)
                .map(this::calcExpiry)
                .map(pair -> this.build(
                        email, name,
                        pair.get(Claims.ISSUED_AT),
                        pair.get(Claims.EXPIRATION))).collect(Collectors.toList());

        return new JwtPair(tokens);
    }
    private Map<String, Date> calcExpiry(String expiryAsString) {
        Instant now = Instant.now();
        long expiry = Long.parseLong(expiryAsString);

        Date iat = new Date(now.toEpochMilli());
        Date exp = new Date(now.plusSeconds(expiry).toEpochMilli());

        return Map.of(
                Claims.ISSUED_AT, iat,
                Claims.EXPIRATION, exp);
    }

    public Optional<Claims> validate(Optional<String> token) {
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(jwtProperties.getKey())
                .requireIssuer(jwtProperties.getIssuer())
                .build();

        try {
            return token.flatMap(jwt -> {
                try {
                    Jws<Claims> claimsJws = parser.parseClaimsJws(jwt);
                    if (!claimsJws.getBody().getExpiration().before(new Date())) {
                        return Optional.of(claimsJws.getBody());
                    }
                } catch (JwtException e) {
                    log.debug(e.getMessage());
                }
                return Optional.empty();
            });

        } catch (JwtException e) {
            log.debug(e.getMessage());
        }
        return Optional.empty();
    }
    private String build(String email,String name, Date iat, Date exp) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setIssuer(jwtProperties.getIssuer())
                .setSubject(email)
                .claim("name", name)
                .claim("rol", Role.USER.name())
                .setIssuedAt(iat)
                .setExpiration(exp)
                .signWith(jwtProperties.getKey())
                .compact();
    }
}

