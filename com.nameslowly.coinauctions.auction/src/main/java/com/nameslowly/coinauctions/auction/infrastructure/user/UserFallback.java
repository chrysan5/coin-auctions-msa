package com.nameslowly.coinauctions.auction.infrastructure.user;

import org.springframework.stereotype.Component;

@Component
public class UserFallback implements UserFeignClient {

    @Override
    public UserDto getUser(Long userId) {
        return new UserDto();
    }

}
