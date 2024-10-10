package com.nameslowly.coinauctions.auction.infrastructure.user;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserFallbackFactory implements FallbackFactory<UserFeignClient> {

    @Override
    public UserFeignClient create(Throwable cause) {
        return new UserFallback();
    }
}
