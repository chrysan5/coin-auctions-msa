package com.nameslowly.coinauctions.bidwin.presentation.response;

import com.nameslowly.coinauctions.bidwin.domain.model.Bid;
import com.nameslowly.coinauctions.bidwin.domain.model.BidStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RetrieveBidPageResponse implements Serializable {

    private Long id;
    private Long auctionId;
    private String participantMemberUsername;
    private Long coinId;
    private BigDecimal coinAmount;
    private LocalDateTime bidTime;

    public static RetrieveBidPageResponse of(Bid bid) {
        return RetrieveBidPageResponse.builder()
            .id(bid.getId())
            .auctionId(bid.getAuctionId())
            .participantMemberUsername(bid.getParticipantMemberUsername())
            .coinId(bid.getCoinId())
            .coinAmount(bid.getCoinAmount())
            .bidTime(bid.getBidTime())
            .build();
    }

}
