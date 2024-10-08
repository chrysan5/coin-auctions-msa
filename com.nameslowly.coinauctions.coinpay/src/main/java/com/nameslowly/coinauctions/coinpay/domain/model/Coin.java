package com.nameslowly.coinauctions.coinpay.domain.model;

import com.nameslowly.coinauctions.common.shared.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@ToString
@Builder
@Table(name = "p_coins")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coin extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="coin_id")
    private Long id;

    @Column(name = "coin_name", nullable = false, unique = true)
    private String coinName;
    @Column(name = "coin_real_name", nullable = false, unique = true)
    private String coinRealName;
    @Column(precision = 10, scale = 2, name = "coin_price", nullable = false, unique = true)
    private BigDecimal coinPrice;
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    // Getters and Setters
    @Builder
    public Coin(String coinName, String coinRealName, BigDecimal coinPrice) {
        this.coinName = coinName;
        this.coinRealName = coinRealName;
        this.coinPrice = coinPrice;
        this.isDeleted = false;
    }
    public void coinUpdate(BigDecimal price){
        this.coinPrice = price;
    }
    public void coinDelete(){
        this.isDeleted = true;
    }
    public CoinVO toCoinVO() {
        return new CoinVO(id, coinName, coinRealName, coinPrice);
    }
}