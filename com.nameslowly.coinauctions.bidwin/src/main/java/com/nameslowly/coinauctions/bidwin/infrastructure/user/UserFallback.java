package com.nameslowly.coinauctions.bidwin.infrastructure.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserFallback implements UserFeignClient {

    @Override
    public UserDto getUser() {
        log.info("user service error");
//        throw new RuntimeException("user service error");
        return null;
    }
}
