package com.nameslowly.coinauctions.auction.application;

import com.nameslowly.coinauctions.auction.domain.service.AuctionStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private final AuctionStore auctionStore;

    @Transactional
    public Long register() {
        return null;
    }

}
