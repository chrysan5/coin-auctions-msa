package com.nameslowly.coinauctions.bidwin.infrastructure.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;

@FeignClient(name = "user-service", fallbackFactory = UserFallbackFactory.class)
@Primary
public interface UserFeignClient extends UserService {

}
