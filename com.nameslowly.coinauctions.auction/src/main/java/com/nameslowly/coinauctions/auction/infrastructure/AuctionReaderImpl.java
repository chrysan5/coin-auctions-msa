package com.nameslowly.coinauctions.auction.infrastructure;

import com.nameslowly.coinauctions.auction.domain.model.Auction;
import com.nameslowly.coinauctions.auction.domain.model.AuctionStatus;
import com.nameslowly.coinauctions.auction.domain.repository.AuctionRepository;
import com.nameslowly.coinauctions.auction.domain.service.AuctionReader;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuctionReaderImpl implements AuctionReader {

    private final AuctionRepository auctionRepository;


    @Override
    public List<Auction> getPendingAuction() {
        return auctionRepository.findByAuctionStatus(AuctionStatus.PENDING);
    }

    @Override
    public List<Auction> getOngoingAuction() {
        return auctionRepository.findByAuctionStatus(AuctionStatus.ONGOING);
    }

}
