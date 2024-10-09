package com.nameslowly.coinauctions.bidwin.infrastructure.coinpay;

import java.math.BigDecimal;

public interface CoinpayService {

    CoinDto getCoin(Long coinId);

    void decreaseUserCoin(BigDecimal coinAmount);

    void increaseUserCoin(BigDecimal coinAmount);
}
