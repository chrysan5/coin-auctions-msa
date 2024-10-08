package com.nameslowly.coinauctions.bidwin.domain.service;

import com.nameslowly.coinauctions.bidwin.domain.model.Bid;

public abstract class BidStore {

    public abstract Bid store(Bid initBid);
}
