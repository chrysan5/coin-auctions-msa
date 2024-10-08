package com.nameslowly.coinauctions.coinpay.domain.repository;

import com.nameslowly.coinauctions.coinpay.domain.model.CoinHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinHistoryRepository extends JpaRepository<CoinHistory, Long> {

}
