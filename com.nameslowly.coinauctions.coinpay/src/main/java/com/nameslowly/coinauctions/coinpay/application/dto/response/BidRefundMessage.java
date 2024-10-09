package com.nameslowly.coinauctions.coinpay.application.dto.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidRefundMessage {
    private String username;
    private Long coin_id;
    private double amount;
}
