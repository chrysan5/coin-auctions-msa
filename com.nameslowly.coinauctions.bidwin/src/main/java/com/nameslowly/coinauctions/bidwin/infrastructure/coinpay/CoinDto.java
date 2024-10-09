package com.nameslowly.coinauctions.bidwin.infrastructure.coinpay;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CoinDto {

    private Long id;
    private String coin_name;
    private String coin_real_name;
    private BigDecimal coin_price;
}
