package com.nameslowly.coinauctions.bidwin.infrastructure;

import com.nameslowly.coinauctions.bidwin.infrastructure.coinpay.CoinDto;
import com.nameslowly.coinauctions.bidwin.infrastructure.coinpay.CoinpayService;
import org.springframework.stereotype.Component;

@Component
public class CoinServiceImpl implements CoinpayService {

    @Override
    public CoinDto getCoin() {
        return null;
    }
}
