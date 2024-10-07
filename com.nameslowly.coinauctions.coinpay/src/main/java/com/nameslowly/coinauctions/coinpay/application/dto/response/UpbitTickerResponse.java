package com.nameslowly.coinauctions.coinpay.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class UpbitTickerResponse {
    @JsonProperty("trade_price")
    private BigDecimal tradePrice;

    public BigDecimal getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(BigDecimal tradePrice) {
        this.tradePrice = tradePrice;
    }
}


