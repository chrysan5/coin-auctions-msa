package com.nameslowly.coinauctions.coinpay.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoinCreateRequest {
    private String coin_real_name;
    private String coin_name;
}

