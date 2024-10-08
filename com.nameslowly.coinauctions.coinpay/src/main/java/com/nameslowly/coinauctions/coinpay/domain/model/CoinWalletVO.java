package com.nameslowly.coinauctions.coinpay.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class CoinWalletVO {
    private Long id;
    private String username;
    private Long coin_id;
    private BigDecimal quantity;
}
