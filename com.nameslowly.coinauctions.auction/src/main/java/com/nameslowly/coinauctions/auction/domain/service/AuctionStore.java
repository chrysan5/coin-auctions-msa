package com.nameslowly.coinauctions.auction.domain.service;

import com.nameslowly.coinauctions.auction.domain.model.Auction;

public interface AuctionStore {

    public abstract Auction store(Auction initAuction);
}
