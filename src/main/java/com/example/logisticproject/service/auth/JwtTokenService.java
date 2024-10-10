package com.example.logisticproject.service.auth;

import com.example.logisticproject.utils.JwtUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service
@Slf4j
public class JwtTokenService {

    private final JwtUtil jwtUtil;
    private final String tokenSecret;

    public JwtTokenService(JwtUtil jwtUtil, @Value("${spring.security.jwt.secret-key}") String tokenSecret) {
        this.jwtUtil = jwtUtil;
        this.tokenSecret = tokenSecret;
    }

    public Boolean isValid(String token) {
        return jwtUtil.isTokenValid(token, getTokenSecret());
    }

    public String generateToken(@NonNull String subject) {
        return jwtUtil.jwt(new HashMap<>(), subject, getTokenSecret());
    }

    public String subject(String token) {
        return jwtUtil.getSubject(token, getTokenSecret());
    }

    private String getTokenSecret() {
        return tokenSecret;
    }
}
