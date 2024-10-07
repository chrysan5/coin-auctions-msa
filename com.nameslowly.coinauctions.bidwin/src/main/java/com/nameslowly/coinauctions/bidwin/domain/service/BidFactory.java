package com.nameslowly.coinauctions.bidwin.domain.service;

import com.nameslowly.coinauctions.bidwin.application.dto.RegisterBidDto;
import com.nameslowly.coinauctions.bidwin.domain.model.Bid;
import org.springframework.transaction.annotation.Transactional;

public interface BidFactory {

    @Transactional
    Bid create(RegisterBidDto dto);
}
