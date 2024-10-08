package com.nameslowly.coinauctions.bidwin.infrastructure.coinpay;

import java.math.BigDecimal;

public interface CoinpayService {

    CoinDto getCoin(Long coinId);

    void decreaseUserCoin(Long userId, BigDecimal coinAmount);

    void increaseUserCoin(Long userId, BigDecimal coinAmount);
}
