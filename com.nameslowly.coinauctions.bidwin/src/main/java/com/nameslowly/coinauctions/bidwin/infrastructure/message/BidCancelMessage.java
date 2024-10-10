package com.nameslowly.coinauctions.bidwin.infrastructure.message;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BidCancelMessage {

    private String username;
    private Long coin_id;
    private BigDecimal Amount;
}
