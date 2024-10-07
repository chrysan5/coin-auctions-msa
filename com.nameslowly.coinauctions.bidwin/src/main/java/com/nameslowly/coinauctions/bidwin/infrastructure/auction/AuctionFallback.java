package com.nameslowly.coinauctions.bidwin.infrastructure.auction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuctionFallback implements AuctionFeignClient {

    @Override
    public AuctionDto getAuction() {
        log.info("auction service error");
//        throw new RuntimeException("user service error");
        return null;
    }
}
