package com.example.shawn.controller;

import com.example.shawn.dto.ApiResponse;
import com.example.shawn.entity.User;
import com.example.shawn.service.AuthService;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ApiResponse<User> register(@RequestBody AuthRequest request) {
        return ApiResponse.ok(authService.register(request.getUsername(), request.getPassword()));
    }

    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody AuthRequest request) {
        String token = authService.login(request.getUsername(), request.getPassword());
        return ApiResponse.ok("登录成功", token);
    }

    @PostMapping("/logout")
    public ApiResponse<String> logout() {
        return ApiResponse.ok("注销成功", null);
    }

    @Data
    public static class AuthRequest {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }
}
