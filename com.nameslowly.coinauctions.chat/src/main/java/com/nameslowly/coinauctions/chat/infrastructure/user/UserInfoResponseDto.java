package com.nameslowly.coinauctions.chat.infrastructure.user;

import com.nameslowly.coinauctions.common.shared.UserRole;

public record UserInfoResponseDto(
    String username,
    UserRole role
) {

}
