package com.nameslowly.coinauctions.bidwin.infrastructure.auction;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class AuctionFallbackFactory implements FallbackFactory<AuctionFeignClient> {

    @Override
    public AuctionFeignClient create(Throwable cause) {
        return new AuctionFallback();
    }
}
