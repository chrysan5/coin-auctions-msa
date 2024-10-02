package com.nameslowly.coinauctions.bidwin.application.dto.request;

import com.nameslowly.coinauctions.bidwin.domain.model.Bid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBidRequestDto {

    private Long auctionId;
    private Long participantMemberId;
    private Long coinId;
    private Long coinAmount;

    public Bid toEntity() {
        return Bid.builder()
            .auctionId(this.auctionId)
            .participantMemberId(this.participantMemberId)
            .coinId(this.coinId)
            .coinAmount(this.coinAmount)
            .build();
    }
}
