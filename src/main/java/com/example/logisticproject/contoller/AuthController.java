package com.example.logisticproject.contoller;

import com.example.logisticproject.dto.DataDto;
import com.example.logisticproject.dto.req.AuthLoginDto;
import com.example.logisticproject.dto.req.UserRegisterDto;
import com.example.logisticproject.dto.req.UserVerifyDto;
import com.example.logisticproject.dto.resp.LoginResponse;
import com.example.logisticproject.dto.resp.UserMeResponse;
import com.example.logisticproject.service.auth.AuthService;
import com.example.logisticproject.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Tag(name = "Auth controller")
@RequestMapping(ApiConstants.API_VERSION + "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(description = "API for logging users")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid AuthLoginDto dto) {
        return ResponseEntity.ok(authService.login(dto));
    }

    @PostMapping("/register")
    @Operation(description = "API for registering users")
    public ResponseEntity<DataDto<UUID>> register(@RequestBody @Valid UserRegisterDto dto) {
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping(value = "/verify")
    @Operation(description = "Verify verification code is match or not for user")
    public ResponseEntity<DataDto<Boolean>> verify(@RequestBody @Valid UserVerifyDto dto) {
        return ResponseEntity.ok(authService.verify(dto));
    }

    @GetMapping("/me")
    @Operation(description = "API for users me")
    @PreAuthorize(value = "isAuthenticated()")
    public ResponseEntity<UserMeResponse> me() {
        return ResponseEntity.ok(authService.me());
    }

    @PostMapping(value = "/logout")
    @PreAuthorize(value = "isAuthenticated()")
    @Operation(description = "Logging out from the platform")
    public ResponseEntity<Boolean> logout() {
        return ResponseEntity.ok(authService.logout());
    }

//    @GetMapping("/regions")
//    @Operation(description = "get regions")
//    public ResponseEntity<DataDto<List<RegionDto>>> getRegions() {
//        return ResponseEntity.ok(authService.getRegions());
//    }
//
//    @GetMapping("/districts/{id}")
//    @Operation(description = "get districts by region id")
//    public ResponseEntity<DataDto<List<DistrictDto>>> getDistricts(@PathVariable UUID id) {
//        return ResponseEntity.ok(authService.getDistricts(id));
//    }
}
