package com.nameslowly.coinauctions.auction.infrastructure;

import com.nameslowly.coinauctions.auction.domain.model.Auction;
import com.nameslowly.coinauctions.auction.domain.repository.AuctionRepository;
import com.nameslowly.coinauctions.auction.domain.service.AuctionStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuctionStoreImpl implements AuctionStore {

    private final AuctionRepository auctionRepository;

    @Override
    public Auction store(Auction initAuction) {
        return auctionRepository.save(initAuction);
    }
}
