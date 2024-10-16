package com.nameslowly.coinauctions.auction.infrastructure.message;

import com.nameslowly.coinauctions.auction.application.dto.request.BidAuctionDto;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BidRegisterMessage {

    private Long auctionId;
    private String bidUserUsername;
    private BigDecimal bidAmount;

    public BidAuctionDto toDto() {
        return BidAuctionDto.builder()
            .auctionId(this.auctionId)
            .bidUserUsername(this.bidUserUsername)
            .bidAmount(this.bidAmount)
            .build();
    }

}
