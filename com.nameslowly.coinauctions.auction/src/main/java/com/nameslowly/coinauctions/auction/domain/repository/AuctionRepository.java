package com.nameslowly.coinauctions.auction.domain.repository;

import com.nameslowly.coinauctions.auction.domain.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

}
