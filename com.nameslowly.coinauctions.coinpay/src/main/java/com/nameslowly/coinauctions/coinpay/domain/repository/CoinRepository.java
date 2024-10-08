package com.nameslowly.coinauctions.coinpay.domain.repository;

import com.nameslowly.coinauctions.coinpay.domain.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {
    Optional<Coin> findByIdAndIsDeletedFalse(Long coinId);

    Coin findByCoinName(String coinName);

    List<Coin> findByIsDeletedFalse();
}
