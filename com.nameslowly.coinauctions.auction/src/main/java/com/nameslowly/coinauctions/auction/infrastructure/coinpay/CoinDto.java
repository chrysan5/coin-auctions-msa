package com.nameslowly.coinauctions.auction.infrastructure.coinpay;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CoinDto {

    private Long coinId;
    private BigDecimal coinPrice;
}
