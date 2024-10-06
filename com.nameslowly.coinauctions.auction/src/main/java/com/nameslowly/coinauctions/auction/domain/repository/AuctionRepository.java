package com.nameslowly.coinauctions.auction.domain.repository;

import com.nameslowly.coinauctions.auction.domain.model.Auction;
import com.nameslowly.coinauctions.auction.domain.model.AuctionStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

    List<Auction> findByAuctionStatus(AuctionStatus auctionStatus);
}
