package com.nameslowly.coinauctions.bidwin.domain.service;

import com.nameslowly.coinauctions.bidwin.domain.model.Bid;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface BidReader {

    Optional<Bid> findWinBidByAuctionId(Long auctionID);

    List<Bid> getBidPage(Pageable page);

    Bid getBid(Long bidId);
}
