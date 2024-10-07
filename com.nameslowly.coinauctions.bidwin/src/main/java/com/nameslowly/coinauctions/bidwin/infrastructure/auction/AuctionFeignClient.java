package com.nameslowly.coinauctions.bidwin.infrastructure.auction;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;

@FeignClient(name = "auction-service", fallbackFactory = AuctionFallbackFactory.class)
@Primary
public interface AuctionFeignClient extends AuctionService {


}
