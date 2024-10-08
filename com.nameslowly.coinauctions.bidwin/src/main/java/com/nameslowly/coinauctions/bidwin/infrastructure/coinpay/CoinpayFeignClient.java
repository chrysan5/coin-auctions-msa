package com.nameslowly.coinauctions.bidwin.infrastructure.coinpay;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "coinpay-service", fallbackFactory = CoinpayFallbackFactory.class)
@Primary
public interface CoinpayFeignClient extends CoinpayService {

    @GetMapping("/api/coinpays")
    CoinDto getCoin(Long coinId);
}
