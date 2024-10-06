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
    private final AuctionReader auctionReader;

    @Transactional
    public Long register(RegisterAuctionDto dto) {
        Auction initAuction = dto.toEntity();
        Auction auction = auctionStore.store(initAuction);
        return auction.getId();
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void startAuction() {
        List<Auction> pendingAuctions = auctionReader.getPendingAuction();
        pendingAuctions.forEach(
            auction -> {
                BigDecimal fixedCoinPrice = new BigDecimal(3333.33); // todo : coin price 조회
                auction.start(fixedCoinPrice);
            }
        );
    }

    @Scheduled(fixedRate = 1000)
    @Transactional
    public void endAuction() {
        List<Auction> ongoingAuctions = auctionReader.getOngoingAuction();
        ongoingAuctions.forEach(Auction::end);
    }

    @Transactional(readOnly = true)
    public List<Auction> retrieveAuctionPage(Pageable page) {
        return auctionReader.getAuctionPage(page);
    }

    @Transactional(readOnly = true)
    public Auction retrieveAuction(Long auctionId) {
        return auctionReader.getAuction(auctionId);
    }
}
