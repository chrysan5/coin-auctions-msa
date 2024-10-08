package com.nameslowly.coinauctions.bidwin.infrastructure.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", fallbackFactory = UserFallbackFactory.class)
@Primary
public interface UserFeignClient extends UserService {

    @GetMapping("/api/internal/users/{userId}")
    UserDto getUser(@PathVariable Long userId);
}
