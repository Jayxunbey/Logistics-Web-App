package com.example.logisticproject.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Component
public class JwtUtil {
    public String getSubject(String token, String secret) {
        Claims claims = extractAllClaims(token, secret);
        return claims.getSubject();
    }

    public String jwt(Map<String, Object> extraClaims, @NonNull final String subject, @NonNull final String secret) {
        Instant now = Instant.now(Clock.systemDefaultZone());
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(subject)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(5, ChronoUnit.HOURS)))
                .signWith(getSignInKey(secret), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, String secret) {
        if (!StringUtils.hasText(token))
            return false;

        if (expireToken(token, secret))
            return false;

        return Objects.nonNull(getSubject(token, secret));
    }

    public boolean expireToken(String token, String secret) {
        Date expiration = getExpiration(token, secret);
        if (expiration == null)
            return true;
        return expiration.before(Date.from(Instant.now(Clock.systemDefaultZone())));
    }


    private Date getExpiration(String token, String secret) {
        Date date = getClaims(token, Claims::getExpiration, secret);
        if (date != null)
            return date;
        return null;
    }

    public <T> T getClaims(String token, Function<Claims, T> clamsResolver, String secret) {
        final Claims claims = extractAllClaims(token, secret);
        return clamsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token, String secret) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey(secret))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey(String secret) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
