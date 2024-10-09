package com.nameslowly.coinauctions.bidwin.infrastructure.auction;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auction-server", fallbackFactory = AuctionFallbackFactory.class)
@Primary
public interface AuctionFeignClient extends AuctionService {

    @GetMapping("/api/internal/auctions/{auctionId}")
    AuctionDto getAuction(@PathVariable("auctionId") Long auctionId);

}
