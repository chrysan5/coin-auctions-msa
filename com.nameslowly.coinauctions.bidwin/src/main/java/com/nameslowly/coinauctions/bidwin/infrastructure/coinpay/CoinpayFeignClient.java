package com.nameslowly.coinauctions.bidwin.infrastructure.coinpay;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;

@FeignClient(name = "coinpay-service", fallbackFactory = CoinpayFallbackFactory.class)
@Primary
public interface CoinpayFeignClient extends CoinpayService {


}
