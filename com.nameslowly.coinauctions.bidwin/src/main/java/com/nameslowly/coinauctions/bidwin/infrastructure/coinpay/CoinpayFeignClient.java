package com.nameslowly.coinauctions.bidwin.infrastructure.coinpay;

import java.math.BigDecimal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "coinpay-server", fallbackFactory = CoinpayFallbackFactory.class)
@Primary
public interface CoinpayFeignClient extends CoinpayService {

    @GetMapping("/api/internal/coins/{coinId}")
    CoinDto getCoin(Long coinId);

    @PutMapping("/api/internal/coins/decrease")
    void decreaseUserCoin(@RequestBody BigDecimal coinAmount);

    @PutMapping("/api/internal/coins/increase")
    void increaseUserCoin(@RequestBody BigDecimal coinAmount);

}
