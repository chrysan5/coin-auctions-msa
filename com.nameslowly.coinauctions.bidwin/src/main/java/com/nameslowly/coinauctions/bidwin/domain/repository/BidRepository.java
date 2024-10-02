package com.nameslowly.coinauctions.bidwin.domain.repository;

import com.nameslowly.coinauctions.bidwin.domain.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Long> {

}
