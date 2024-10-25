package com.nameslowly.coinauctions.coinpay.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoinExchangeRequest {
    private Long bf_coin_id;
    private Long at_coin_id;
}
