package com.nameslowly.coinauctions.coinpay.domain.repository;

import com.nameslowly.coinauctions.coinpay.domain.model.CoinWallet;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinWalletRepository extends JpaRepository<CoinWallet, Long> {
    Optional<CoinWallet> findByUsernameAndCoinId(String username, Long coinId);

    List<CoinWallet> findByUsername(String username);
}
