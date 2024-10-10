package com.example.logisticproject.service.auth;

import com.example.logisticproject.dto.DataDto;
import com.example.logisticproject.dto.req.AuthLoginDto;
import com.example.logisticproject.dto.req.UserRegisterDto;
import com.example.logisticproject.dto.req.UserVerifyDto;
import com.example.logisticproject.dto.resp.LoginResponse;
import com.example.logisticproject.dto.resp.UserMeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    public LoginResponse login(AuthLoginDto dto) {
        return null;
    }

    public DataDto<UUID> register(UserRegisterDto dto) {
        return null;
    }

    public DataDto<Boolean> verify(UserVerifyDto dto) {
        return null;
    }

    public UserMeResponse me() {
        return null;
    }

    public Boolean logout() {
        return null;
    }
}
