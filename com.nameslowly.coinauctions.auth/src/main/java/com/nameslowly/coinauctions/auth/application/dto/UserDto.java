package com.nameslowly.coinauctions.auth.application.dto;

import com.nameslowly.coinauctions.common.shared.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// LocalDateTime 을 제거한 redis 캐싱용 Dto

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private String username;
    private String password;
    private UserRole role;

}
