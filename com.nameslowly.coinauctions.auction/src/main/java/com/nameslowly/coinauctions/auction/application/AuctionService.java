package com.nameslowly.coinauctions.auction.application;

import com.nameslowly.coinauctions.auction.application.dto.request.RegisterAuctionDto;
import com.nameslowly.coinauctions.auction.domain.model.Auction;
import com.nameslowly.coinauctions.auction.domain.service.AuctionReader;
import com.nameslowly.coinauctions.auction.domain.service.AuctionStore;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private final AuctionStore auctionStore;

    @Transactional
    public Long register(RegisterAuctionDto dto) {
        Auction initAuction = dto.toEntity();
        Auction auction = auctionStore.store(initAuction);
        return auction.getId();
    }

}
