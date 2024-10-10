package com.nameslowly.coinauctions.auction.presentation.response;

import com.nameslowly.coinauctions.auction.domain.model.Auction;
import com.nameslowly.coinauctions.auction.domain.model.AuctionStatus;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AuctionDto implements Serializable {

    private Long id;
    private String title;
    private String image;
    private String description;
    private AuctionStatus auctionStatus;
    private LocalDateTime registerTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BigDecimal basePrice;
    private Long coinId;
    private BigDecimal fixedCoinPrice;
    private Long registerMemberId;
    private BigDecimal currentAmount;

    public static AuctionDto of(Auction auction) {
        return AuctionDto.builder()
            .id(auction.getId())
            .title(auction.getTitle())
            .image(auction.getImage())
            .description(auction.getDescription())
            .auctionStatus(auction.getAuctionStatus())
            .registerTime(auction.getRegisterTime())
            .startTime(auction.getStartTime())
            .endTime(auction.getEndTime())
            .basePrice(auction.getBasePrice())
            .coinId(auction.getCoinId())
            .fixedCoinPrice(auction.getFixedCoinPrice())
            .registerMemberId(auction.getRegisterMemberId())
            .currentAmount(auction.getCurrentAmount())
            .build();
    }

}
