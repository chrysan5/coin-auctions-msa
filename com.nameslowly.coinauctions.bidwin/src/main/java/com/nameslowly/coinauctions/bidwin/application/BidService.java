package com.nameslowly.coinauctions.bidwin.application;

import com.nameslowly.coinauctions.bidwin.application.dto.request.RegisterBidRequestDto;
import com.nameslowly.coinauctions.bidwin.domain.model.Bid;
import com.nameslowly.coinauctions.bidwin.domain.service.BidStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BidService {

    private final BidStore bidStore;

    @Transactional
    public Long register(RegisterBidRequestDto dto) {
        Bid initBid = dto.toEntity();
        Bid bid = bidStore.store(initBid);
        return bid.getId();
    }

}
