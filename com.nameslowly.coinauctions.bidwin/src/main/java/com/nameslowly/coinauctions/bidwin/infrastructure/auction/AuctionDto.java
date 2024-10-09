package com.nameslowly.coinauctions.bidwin.infrastructure.auction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuctionDto {

    private Long auctionId;
    private String title;
    private String description;
    private String auctionStatus;
    private LocalDateTime registerTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BigDecimal basePrice;
    private Long coinId;
    private BigDecimal coinFixedPrice;
    private Long registerMemberId;
    private BigDecimal currentPrice;

}
