package com.nameslowly.coinauctions.bidwin.presentation.request;

import com.nameslowly.coinauctions.bidwin.application.dto.RegisterBidDto;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBidRequest {

    private Long auctionId;
    private String participantMemberUsername;
    private Long coinId;
    private BigDecimal coinAmount;

    public RegisterBidDto toDto() {
        return RegisterBidDto.builder()
            .auctionId(this.auctionId)
            .participantMemberUsername(this.participantMemberUsername)
            .coinId(this.coinId)
            .coinAmount(this.coinAmount)
            .build();
    }
}
