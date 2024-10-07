package com.nameslowly.coinauctions.coinpay.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class CoinVO implements Serializable {
    private Long id;
    private String coin_name;
    private String coin_real_name;
    private BigDecimal coin_price;
}