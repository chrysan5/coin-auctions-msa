package com.nameslowly.coinauctions.auction.application.dto.request;

import com.nameslowly.coinauctions.auction.domain.model.Auction;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RegisterAuctionDto {

    private String title;
    private String image;
    private String description;
    private BigDecimal hopePrice;
    private Long coinId;
    private Long registerMemberId;

    public Auction toEntity() {
        return Auction.builder()
            .title(this.title)
            .image(this.image)
            .description(this.description)
            .basePrice(this.hopePrice)
            .coinId(this.coinId)
            .registerMemberId(this.registerMemberId)
            .build();
    }

}
