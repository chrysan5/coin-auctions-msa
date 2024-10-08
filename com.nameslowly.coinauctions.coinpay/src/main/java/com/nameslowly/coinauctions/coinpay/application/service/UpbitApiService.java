package com.nameslowly.coinauctions.coinpay.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nameslowly.coinauctions.coinpay.application.dto.response.UpbitTickerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UpbitApiService {

    private final RestTemplate restTemplate;
    private final ObjectMapper jacksonObjectMapper;

    public BigDecimal fetchCoinPrice(String coinRealName) {
        // Upbit API URL 설정
        String url = "https://api.upbit.com/v1/ticker?markets=" + coinRealName;
        log.info("Fetching upbit ticker from " + url);
        try {
            // API 호출 (응답을 배열로 받음)
            ResponseEntity<UpbitTickerResponse[]> response = restTemplate.getForEntity(url, UpbitTickerResponse[].class);
            UpbitTickerResponse[] tickerData = response.getBody();
            if (tickerData != null) {
                String jsonString = jacksonObjectMapper.writeValueAsString(tickerData);
                log.info("Upbit ticker data: " + jsonString);
            }
            // 첫 번째 객체의 trade_price (실시간 가격) 반환
            if (tickerData != null && tickerData.length > 0) {
                return tickerData[0].getTradePrice();
            } else {
                throw new RuntimeException("No data returned from Upbit API.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching coin price from Upbit: " + e.getMessage());
        }
    }
}
