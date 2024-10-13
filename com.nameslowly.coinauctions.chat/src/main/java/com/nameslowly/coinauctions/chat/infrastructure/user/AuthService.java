package com.nameslowly.coinauctions.chat.infrastructure.user;

public interface AuthService {

    UserInfoResponseDto getUser(String username);
}
