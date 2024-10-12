package com.nameslowly.coinauctions.auth.presentation.controller;

import com.nameslowly.coinauctions.auth.application.dto.UserInfoResponseDto;
import com.nameslowly.coinauctions.auth.application.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthFeignController {

    private final AuthService authService;

    @GetMapping("/api/auth/internal/{username}")
    public UserInfoResponseDto getUser(@PathVariable String username) {
        UserInfoResponseDto userInfoResponseDto = authService.getUserByUsername(username);
        return userInfoResponseDto;
    }

}
