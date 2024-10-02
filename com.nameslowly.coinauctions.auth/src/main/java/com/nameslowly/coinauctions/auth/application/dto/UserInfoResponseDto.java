package com.nameslowly.coinauctions.auth.application.dto;

import com.nameslowly.coinauctions.common.shared.UserRole;

public record UserInfoResponseDto(
    String username,
    UserRole role
) {

}
