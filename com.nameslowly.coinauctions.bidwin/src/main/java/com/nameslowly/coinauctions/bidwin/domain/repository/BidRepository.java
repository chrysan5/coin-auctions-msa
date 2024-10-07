package com.nameslowly.coinauctions.bidwin.domain.repository;

import com.nameslowly.coinauctions.bidwin.domain.model.Bid;
import com.nameslowly.coinauctions.bidwin.domain.model.BidStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Long>, BidRepositoryCustom {

    Optional<Bid> findByAuctionIdAndBidStatus(Long auctionID, BidStatus bidStatus);

    Optional<Bid> findFirstByAuctionIdAndBidStatusOrderByIdDesc(Long auctionID,
        BidStatus bidStatus);
}
