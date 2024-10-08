package com.nameslowly.coinauctions.bidwin.infrastructure.coinpay;

import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CoinpayFallback implements CoinpayFeignClient {

    @Override
    public CoinDto getCoin(Long coinId) {
        log.info("coin service error");
//        throw new RuntimeException("user service error");
        return null;
    }

    @Override
    public void decreaseUserCoin(Long userId, BigDecimal coinAmount) {

    }

    @Override
    public void increaseUserCoin(Long userId, BigDecimal coinAmount) {

    }
}
