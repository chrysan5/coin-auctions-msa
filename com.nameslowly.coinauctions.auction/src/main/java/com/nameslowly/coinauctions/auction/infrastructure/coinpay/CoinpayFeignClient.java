package com.nameslowly.coinauctions.auction.infrastructure.coinpay;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "coinpay-server", fallbackFactory = CoinpayFallbackFactory.class)
@Primary
public interface CoinpayFeignClient extends CoinpayService {

    @GetMapping("/api/internal/coins/{coinId}")
    CoinDto getCoin(@PathVariable("coinId") Long coinId);


}
