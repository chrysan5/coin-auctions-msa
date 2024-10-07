package com.nameslowly.coinauctions.bidwin.infrastructure.auction;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuctionDto {

    private Long auctionId;
    private BigDecimal baseAmount;

}
