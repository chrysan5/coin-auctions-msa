package com.nameslowly.coinauctions.bidwin.infrastructure;

import com.nameslowly.coinauctions.bidwin.application.dto.RegisterBidDto;
import com.nameslowly.coinauctions.bidwin.domain.model.Bid;
import com.nameslowly.coinauctions.bidwin.domain.service.BidFactory;
import com.nameslowly.coinauctions.bidwin.domain.service.BidStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class BidFactoryImpl implements BidFactory {

    private final BidStore bidStore;

    @Override
    @Transactional
    public Bid create(RegisterBidDto dto) {
        Bid initBid = dto.toEntity();
        return bidStore.store(initBid);
    }

}
