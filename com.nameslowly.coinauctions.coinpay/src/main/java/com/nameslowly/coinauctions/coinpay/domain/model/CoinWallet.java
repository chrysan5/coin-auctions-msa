package com.nameslowly.coinauctions.coinpay.domain.model;

import com.nameslowly.coinauctions.common.shared.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@ToString
@Builder
@Table(name = "p_coin_wallets")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CoinWallet extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="coin_wallet_id")
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(name="coin_id", nullable = false)
    private Long coinId;
    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal quantity;

    public CoinWalletVO toCoinWalletVO() {
        return new CoinWalletVO(id, username, coinId, quantity);
    }
    public void coinWalletUpdate(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
