package com.nameslowly.coinauctions.coinpay.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoinBidRequest {
    private String username;
    private Long coin_id;
    private BigDecimal quantity;
}
