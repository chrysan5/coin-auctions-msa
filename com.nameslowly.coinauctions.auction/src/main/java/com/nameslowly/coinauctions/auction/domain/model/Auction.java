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
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "auctions")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Auction extends BaseEntity {

    @Value("${app.operation-costs}")
    private Integer operatingCosts;

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
    private Integer baseAmount;

}
