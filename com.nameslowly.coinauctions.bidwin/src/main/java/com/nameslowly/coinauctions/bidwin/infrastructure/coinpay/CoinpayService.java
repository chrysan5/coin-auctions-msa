package com.nameslowly.coinauctions.bidwin.infrastructure.coinpay;

public interface CoinpayService {

    CoinDto getCoin(Long coinId);

    boolean useCoin(CoinBidRequest coinAmount);

    void recoverCoin(CoinBidRequest coinAmount);
}
