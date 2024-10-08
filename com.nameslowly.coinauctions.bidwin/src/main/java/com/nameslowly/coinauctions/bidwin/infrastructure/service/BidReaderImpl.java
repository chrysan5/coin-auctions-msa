package com.nameslowly.coinauctions.bidwin.infrastructure.service;

import com.nameslowly.coinauctions.bidwin.domain.model.Bid;
import com.nameslowly.coinauctions.bidwin.domain.model.BidStatus;
import com.nameslowly.coinauctions.bidwin.domain.repository.BidRepository;
import com.nameslowly.coinauctions.bidwin.domain.service.BidReader;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BidReaderImpl implements BidReader {

    private final BidRepository bidRepository;

    @Override
    public Optional<Bid> findWinBidByAuctionId(Long auctionID) {
        return bidRepository.findFirstByAuctionIdAndBidStatusOrderByIdDesc(auctionID,
            BidStatus.WIN);
    }

    @Override
    public List<Bid> getBidPage(Pageable page) {
        return bidRepository.findAll(page).getContent();
    }

    @Override
    public Bid getBid(Long bidId) {
        return bidRepository.findById(bidId).orElseThrow();
    }

}
