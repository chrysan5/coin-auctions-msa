package com.nameslowly.coinauctions.chat.infrastructure.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userAuth-service", fallbackFactory = UserFallbackFactory.class)
@Primary
public interface AuthFeignClient extends AuthService {

    @GetMapping("/api/auth/{username}")
    UserInfoResponseDto getUser(@PathVariable String username);
}
