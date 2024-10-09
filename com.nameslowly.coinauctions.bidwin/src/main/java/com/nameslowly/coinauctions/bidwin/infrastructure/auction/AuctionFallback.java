package com.nameslowly.coinauctions.bidwin.infrastructure.auction;

import com.nameslowly.coinauctions.common.exception.GlobalException;
import com.nameslowly.coinauctions.common.response.ResultCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuctionFallback implements AuctionFeignClient {

    @Override
    public AuctionDto getAuction(Long auctionId) {
        log.info("AUCTION-SERVER 에러");
        throw new GlobalException(ResultCase.NOT_FOUND_AUCTION);
    }
}
