package com.nameslowly.coinauctions.bidwin.application.dto;

import com.nameslowly.coinauctions.bidwin.domain.model.Bid;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RegisterBidDto {

    private Long auctionId;
    private String participantMemberUsername;
    private Long coinId;
    private BigDecimal coinAmount;

    public Bid toEntity() {
        return Bid.builder()
            .auctionId(this.auctionId)
            .participantMemberUsername(this.participantMemberUsername)
            .coinId(this.coinId)
            .coinAmount(this.coinAmount)
            .build();
    }
}
