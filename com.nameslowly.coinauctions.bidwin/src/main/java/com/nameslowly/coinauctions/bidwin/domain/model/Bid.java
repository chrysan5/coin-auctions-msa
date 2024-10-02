package com.nameslowly.coinauctions.bidwin.domain.model;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

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

@Entity
@Table(name = "bids")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Bid {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "bid_id")
    private Long id;
    private Long auctionId;
    private Long participantMemberId;
    private Long coinId;
    private Long coinAmount;
    @Enumerated(EnumType.STRING)
    private BidStatus bidStatus;
    private LocalDateTime bidTime;

}
