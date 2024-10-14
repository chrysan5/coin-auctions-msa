package com.nameslowly.coinauctions.auction.infrastructure.user;

import com.nameslowly.coinauctions.common.exception.GlobalException;
import com.nameslowly.coinauctions.common.response.ResultCase;
import org.springframework.stereotype.Component;

@Component
public class UserFallback implements UserFeignClient {

    @Override
    public UserDto getUser(String username) {
        throw new GlobalException(ResultCase.USER_NOT_FOUND);
    }

}
