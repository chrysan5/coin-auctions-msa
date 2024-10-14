package com.nameslowly.coinauctions.auction.infrastructure.coinpay;

import com.nameslowly.coinauctions.common.exception.GlobalException;
import com.nameslowly.coinauctions.common.response.ResultCase;
import java.util.concurrent.CompletionException;
import org.springframework.stereotype.Component;

@Component
public class CoinpayFallback implements CoinpayFeignClient {

    @Override
    public CoinDto getCoin(Long coinId) {
        throw new GlobalException(ResultCase.COIN_NOT_FOUND);
    }

}
