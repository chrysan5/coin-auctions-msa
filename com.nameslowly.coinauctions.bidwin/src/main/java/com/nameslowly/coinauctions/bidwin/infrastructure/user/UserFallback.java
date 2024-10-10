package com.nameslowly.coinauctions.bidwin.infrastructure.user;

import com.nameslowly.coinauctions.common.exception.GlobalException;
import com.nameslowly.coinauctions.common.response.ResultCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserFallback implements UserFeignClient {

    @Override
    public UserDto getUser(Long userId) {
        log.info("USER-SERVER 에러");
        throw new GlobalException(ResultCase.NOT_FOUND_USER);
    }
}
