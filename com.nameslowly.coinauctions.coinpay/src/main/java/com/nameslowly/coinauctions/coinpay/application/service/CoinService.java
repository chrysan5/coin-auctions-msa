package com.nameslowly.coinauctions.coinpay.application.service;

import com.nameslowly.coinauctions.coinpay.domain.model.Coin;
import com.nameslowly.coinauctions.coinpay.domain.model.CoinVO;
import com.nameslowly.coinauctions.coinpay.domain.repository.CoinRepository;
import com.nameslowly.coinauctions.common.exception.GlobalException;
import com.nameslowly.coinauctions.common.response.ResultCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CoinService {

    private final CoinRepository coinRepository;
    private final UpbitApiService upbitApiService;  // Upbit API 호출 서비스

    @Transactional(readOnly = true) //모든 코인 조회
    public List<CoinVO> getAllCoins(){
        List<Coin> coins= coinRepository.findByIsDeletedFalse();
        if (coins.isEmpty()) {
            throw new GlobalException(ResultCase.NO_COINS_FOUND);  // 코인이 없을 경우
        }
        return coins.stream()
                .map(Coin::toCoinVO)
                .collect(Collectors.toList());
    }

    @Scheduled(fixedRate = 5000)  // 5초마다 실행 업비트에서 코인들 가격 갱신
    public void updateCoinPrices() {
        List<Coin> coins = coinRepository.findAll();
        for (Coin coin : coins) {
            try {
                // Upbit API 호출하여 코인 가격 가져오기
                BigDecimal price = upbitApiService.fetchCoinPrice(coin.getCoinRealName());
                log.info("Updating price for coin: " + coin.getCoinRealName() + " with price: " + price);

                // 코인 가격 업데이트
                coin.coinUpdate(price.setScale(2, RoundingMode.HALF_UP));
                coinRepository.save(coin);
            } catch (Exception e) {
                log.error("Error updating price for coin: " + coin.getCoinRealName(), e);
                throw new GlobalException(ResultCase.COIN_PRICE_UPDATE_FAILED);  // 가격 업데이트 실패 시 예외 처리
            }
        }
    }

    // Upbit에서 코인 가격 가져오기
    public BigDecimal getCoinPriceFromUpbit(String coinName) {
        try {
            return upbitApiService.fetchCoinPrice(coinName);
        } catch (Exception e) {
            log.error("Error fetching price from Upbit for coin: " + coinName, e);
            throw new GlobalException(ResultCase.UPBIT_API_ERROR);  // Upbit API 호출 실패 시 예외 처리
        } // Upbit API 호출 메서드
    }
    @Transactional //코인 등록
    public CoinVO saveCoin(String coinName,String coinRealName, BigDecimal coinPrice) {
        if (coinPrice == null || coinPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new GlobalException(ResultCase.INVALID_COIN_PRICE);  // 유효하지 않은 코인 가격
        }
        Coin existingCoin = coinRepository.findByCoinName(coinName);
        if (existingCoin != null) {
            throw new GlobalException(ResultCase.COIN_ALREADY_EXISTS);  // 이미 존재하는 코인
        }
        Coin coin = Coin.builder()
                .coinName(coinName)
                .coinRealName(coinRealName)
                .coinPrice(coinPrice.setScale(2, RoundingMode.HALF_UP))
                .isDeleted(false)
                .build();
        coinRepository.save(coin);
        return coin.toCoinVO();
    }

    @Transactional(readOnly = true) //코인아이디로 코인조회
    public CoinVO getCoinById(Long coinId) {
        Coin coin = coinRepository.findByIdAndIsDeletedFalse(coinId)
                .orElseThrow(()->new GlobalException(ResultCase.COIN_NOT_FOUND));
        if (coin.getCoinPrice() == null || coin.getCoinPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new GlobalException(ResultCase.INVALID_COIN_PRICE);  // 가격이 유효하지 않은 코인
        }
        return coin.toCoinVO();
    }
    @Transactional //코인 삭제 IsDeleted->true
    public CoinVO deleteCoin(Long coinId) {
        Coin coin = coinRepository.findById(coinId)
                .orElseThrow(()->new GlobalException(ResultCase.COIN_NOT_FOUND));
        coin.coinDelete();
        coinRepository.save(coin);
        return coin.toCoinVO();
    }
}
