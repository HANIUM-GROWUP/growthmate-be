package com.growup.growthmate.auth.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final String SECRET_KEY = "SGFuRXVtIEdyb3d0aE1hdGUgQmFja2VuZCBSYW5kb20gRW5jb2RpbmcgU2VjcmV0IEtleQ==";
    private static final String EXPIRE_LENGTH = "86400"; // 24 * 60 * 60 (하루)

    private final SecretKey key;
    private final long validityInMilliseconds;

    public JwtTokenProvider(@Value(SECRET_KEY) final String secretKey,
                            @Value(EXPIRE_LENGTH) final long validityInMilliseconds) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.validityInMilliseconds = validityInMilliseconds;
    }

    public String createToken(TokenPayload payload) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        return Jwts.builder()
                .addClaims(payload.toMap())
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public TokenPayload getPayload(String token) {
        Claims claims = parseClaimsJws(token).getBody();
        Long id = claims.get("id", Long.class);
        return new TokenPayload(id);
    }

    private Jws<Claims> parseClaimsJws(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

    public boolean isValidAccessToken(String accessToken) {
        try {
            return !parseClaimsJws(accessToken)
                    .getBody()
                    .getExpiration()
                    .before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}

