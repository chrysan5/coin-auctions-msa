package com.nameslowly.coinauctions.bidwin.infrastructure.coinpay;

import com.nameslowly.coinauctions.common.exception.GlobalException;
import com.nameslowly.coinauctions.common.response.ResultCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CoinpayFallback implements CoinpayFeignClient {

    @Override
    public CoinDto getCoin(Long coinId) {
        log.info("COINPAY-SERVER 에러");
        throw new GlobalException(ResultCase.COIN_NOT_FOUND);
    }

    @Override
    public boolean useCoin(CoinBidRequest request) {
        log.info("COINPAY-SERVER 에러");
        return false;
    }

    @Override
    public void recoverCoin(CoinBidRequest request) {
        log.info("COINPAY-SERVER 에러");
    }
}
