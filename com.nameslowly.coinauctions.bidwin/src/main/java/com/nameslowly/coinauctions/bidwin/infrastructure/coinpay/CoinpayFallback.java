package com.nameslowly.coinauctions.bidwin.infrastructure.coinpay;

import com.nameslowly.coinauctions.common.exception.GlobalException;
import com.nameslowly.coinauctions.common.response.ResultCase;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CoinpayFallback implements CoinpayFeignClient {

    @Override
    public CoinDto getCoin(Long coinId) {
        log.info("COINPAY-SERVER 에러");
        throw new GlobalException(ResultCase.NOT_FOUND_COIN);
    }

    @Override
    public void decreaseUserCoin(BigDecimal coinAmount) {

    }

    @Override
    public void increaseUserCoin(BigDecimal coinAmount) {

    }
}
