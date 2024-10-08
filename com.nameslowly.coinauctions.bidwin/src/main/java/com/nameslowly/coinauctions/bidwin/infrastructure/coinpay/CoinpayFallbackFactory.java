package com.nameslowly.coinauctions.bidwin.infrastructure.coinpay;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class CoinpayFallbackFactory implements FallbackFactory<CoinpayFeignClient> {

    @Override
    public CoinpayFeignClient create(Throwable cause) {
        return new CoinpayFallback();
    }
}
