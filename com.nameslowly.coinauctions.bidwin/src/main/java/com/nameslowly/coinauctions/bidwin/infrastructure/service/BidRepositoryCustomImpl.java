package com.nameslowly.coinauctions.bidwin.infrastructure.service;

import com.nameslowly.coinauctions.bidwin.domain.repository.BidRepository;
import com.nameslowly.coinauctions.bidwin.domain.repository.BidRepositoryCustom;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BidRepositoryCustomImpl implements BidRepositoryCustom {

    private final BidRepository bidRepository;

}
