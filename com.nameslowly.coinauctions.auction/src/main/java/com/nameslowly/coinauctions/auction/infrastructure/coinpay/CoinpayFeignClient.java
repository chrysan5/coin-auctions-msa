package com.nameslowly.coinauctions.auction.infrastructure.coinpay;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "coinpay-service", fallbackFactory = CoinpayFallbackFactory.class)
@Primary
public interface CoinpayFeignClient extends CoinpayService {

    @GetMapping("/api/internal/coinpays/{coinId}")
    CoinDto getCoin(@PathVariable("coinId") Long coinId);


}
