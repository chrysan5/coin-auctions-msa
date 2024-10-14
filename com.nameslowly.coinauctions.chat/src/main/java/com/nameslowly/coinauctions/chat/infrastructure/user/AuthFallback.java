package com.nameslowly.coinauctions.chat.infrastructure.user;

import com.nameslowly.coinauctions.common.exception.GlobalException;
import com.nameslowly.coinauctions.common.response.ResultCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthFallback implements AuthFeignClient {

    @Override
    public UserInfoResponseDto getUser(String username) {
        log.info("USER-SERVER 에러");
        throw new GlobalException(ResultCase.USER_NOT_FOUND);
    }
}
