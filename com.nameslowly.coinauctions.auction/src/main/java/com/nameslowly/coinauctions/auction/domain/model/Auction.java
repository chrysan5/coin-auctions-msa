package com.nameslowly.coinauctions.auction.domain.model;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.nameslowly.coinauctions.common.shared.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "auctions")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Auction extends BaseEntity {

    private static final BigDecimal OPERATING_COASTS = new BigDecimal(500);
    private static final Integer DURATION = 10;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "auction_id")
    private Long id;
    private String title;
    private String image;
    private String description;
    @Enumerated(EnumType.STRING)
    private AuctionStatus auctionStatus;
    private LocalDateTime registerTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BigDecimal basePrice;
    private Long coinId;
    private BigDecimal fixedCoinPrice;
    private Long registerMemberId;
    private BigDecimal currentAmount;

    @Builder
    public Auction(String title, String image, String description, BigDecimal basePrice,
        Long coinId, Long registerMemberId) {
        this.title = title;
        this.image = image;
        this.description = description;
        this.auctionStatus = AuctionStatus.PENDING;
        this.registerTime = LocalDateTime.now();
        this.basePrice = basePrice.add(OPERATING_COASTS);
        this.coinId = coinId;
        this.registerMemberId = registerMemberId;
    }

    public void start(BigDecimal fixedCoinPrice) {
        if (this.auctionStatus != AuctionStatus.PENDING) {
            return;
        }
        this.startTime = LocalDateTime.now();
        this.endTime = this.startTime.plusMinutes(DURATION);
        this.fixedCoinPrice = fixedCoinPrice;
        this.auctionStatus = AuctionStatus.ONGOING;
    }

    public void end() {
        if (this.auctionStatus != AuctionStatus.ONGOING) {
            return;
        }
        if (this.endTime.isBefore(LocalDateTime.now())) {
            this.auctionStatus =
                this.currentAmount == null ? AuctionStatus.FAIL : AuctionStatus.SUCCESS;
        }
    }

    public void updateCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }
}
