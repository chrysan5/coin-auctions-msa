package com.nameslowly.coinauctions.auction.infrastructure.coinpay;

import org.springframework.stereotype.Component;

@Component
public class CoinpayFallback implements CoinpayFeignClient {

    @Override
    public CoinDto getCoin(Long coinId) {
        return new CoinDto();
    }

}
