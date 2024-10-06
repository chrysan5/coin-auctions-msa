package com.nameslowly.coinauctions.auction.presentation.dto.request;

import com.nameslowly.coinauctions.auction.application.dto.request.RegisterAuctionDto;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterAuctionRequest {

    private String title;
    private String image;
    private String description;
    private BigDecimal hopePrice;
    private Long coinId;
    private Long registerMemberId;

    public RegisterAuctionDto toDto() {
        return RegisterAuctionDto.builder()
            .title(this.title)
            .image(this.image)
            .description(this.description)
            .hopePrice(this.hopePrice)
            .coinId(this.coinId)
            .registerMemberId(this.registerMemberId)
            .build();
    }
}
