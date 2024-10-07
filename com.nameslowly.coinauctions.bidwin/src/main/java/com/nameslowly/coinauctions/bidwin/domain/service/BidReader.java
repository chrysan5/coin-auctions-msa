package com.nameslowly.coinauctions.bidwin.domain.service;

import com.nameslowly.coinauctions.bidwin.domain.model.Bid;
import java.util.Optional;

public interface BidReader {

    Optional<Bid> findWinBidByAuctionId(Long auctionID);
}
