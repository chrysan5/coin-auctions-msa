package com.nameslowly.coinauctions.bidwin.infrastructure.coinpay;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "coinpay-server", fallbackFactory = CoinpayFallbackFactory.class)
@Primary
public interface CoinpayFeignClient extends CoinpayService {

    @GetMapping("/api/internal/coins/{coinId}")
    CoinDto getCoin(@PathVariable("coinId") Long coinId);

    @PutMapping("/api/internal/coin_wallets/use")
    boolean useCoin(@RequestBody CoinBidRequest request);

    @PutMapping("/api/internal/coin_wallets/recover")
    void recoverCoin(@RequestBody CoinBidRequest request);

}
