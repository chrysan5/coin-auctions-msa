package com.nameslowly.coinauctions.coinpay.application.service;


import com.nameslowly.coinauctions.coinpay.application.dto.request.CoinBidRequest;
import com.nameslowly.coinauctions.coinpay.application.dto.request.CoinChargeRequest;
import com.nameslowly.coinauctions.coinpay.application.dto.request.CoinExchangeRequest;
import com.nameslowly.coinauctions.coinpay.application.dto.response.CoinHistoryMessage;
import com.nameslowly.coinauctions.coinpay.domain.model.Coin;
import com.nameslowly.coinauctions.coinpay.domain.model.CoinHistory;
import com.nameslowly.coinauctions.coinpay.domain.model.CoinWallet;
import com.nameslowly.coinauctions.coinpay.domain.model.CoinWalletVO;
import com.nameslowly.coinauctions.coinpay.domain.repository.CoinHistoryRepository;
import com.nameslowly.coinauctions.coinpay.domain.repository.CoinRepository;
import com.nameslowly.coinauctions.coinpay.domain.repository.CoinWalletRepository;
import com.nameslowly.coinauctions.common.exception.GlobalException;
import com.nameslowly.coinauctions.common.response.ResultCase;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CoinWalletService {

    private final CoinRepository coinRepository;
    private final CoinWalletRepository coinWalletRepository;
    private final CoinHistoryService coinHistoryService;
    private final RabbitTemplate rabbitTemplate;

    public void saveCoinHistoryAsync(String username, Long coinId, BigDecimal amount,
        BigDecimal balanceBefore, BigDecimal balanceAfter, String reason) {
        CoinHistoryMessage message = new CoinHistoryMessage(username, coinId, amount, balanceBefore, balanceAfter, reason);
        // 메시지를 지정된 큐로 발행
        rabbitTemplate.convertAndSend("app.coinpay.history", message);
    }

    // 코인 히스토리 생성 함수


    @Transactional //코인 지갑 생성 -> 이미 해당유저와 코인이 있는경우 충전금액 만큼 코인 추가, 없을 경우 생성 -> 코인 히스토리 생성
    public CoinWalletVO saveCoinWallet(CoinChargeRequest request, String username) {
        Coin coin = coinRepository.findByIdAndIsDeletedFalse(request.getCoin_id())
            .orElseThrow(() -> new GlobalException(ResultCase.COIN_NOT_FOUND));

        BigDecimal price = coin.getCoinPrice();

        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new GlobalException(ResultCase.INVALID_COIN_PRICE);
        }

        BigDecimal chargeAmount = request.getCharge_amount();

        if (chargeAmount == null || chargeAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new GlobalException(ResultCase.INVALID_CHARGE_AMOUNT);
        }
        BigDecimal quantity = chargeAmount.divide(price, 2, RoundingMode.HALF_UP);

        // username과 coinId로 기존 CoinWallet을 조회
        CoinWallet coinWallet = coinWalletRepository.findByUsernameAndCoinId(username,
                request.getCoin_id())
            .orElse(null);
        log.info("Saving coin wallet: {}", coinWallet);
        BigDecimal balanceBefore;
        BigDecimal balanceAfter;

        if (coinWallet != null) {
            // 이미 코인 월렛이 존재하는 경우 수량만 업데이트
            balanceBefore = coinWallet.getQuantity();
            balanceAfter = balanceBefore.add(quantity);
            coinWallet.coinWalletUpdate(balanceAfter);  // 기존 수량에 더함
        } else {
            // 새로운 CoinWallet을 생성
            balanceBefore = BigDecimal.ZERO;
            balanceAfter = quantity;
            coinWallet = CoinWallet.builder()
                .username(username)
                .coinId(request.getCoin_id())
                .quantity(quantity)
                .build();
        }

        coinWalletRepository.save(coinWallet);
        // CoinHistory 저장
        coinHistoryService.createCoinHistory(username, request.getCoin_id(), quantity, balanceBefore, balanceAfter,
            "코인 충전");
//        saveCoinHistoryAsync(username, request.getCoin_id(), quantity, balanceBefore, balanceAfter,
//            "코인 충전");
        return coinWallet.toCoinWalletVO();
    }

    @Transactional //코인 바인딩 feign요청 들어왔을 때 입찰한 만큼의 코인 바인딩 후 코인 히스토리로 남김
    public boolean changeBidCoin(CoinBidRequest request) {
        CoinWallet coinWallet = coinWalletRepository.findByUsernameAndCoinId(request.getUsername(),
                request.getCoin_id())
            .orElseThrow(() -> new GlobalException(ResultCase.COIN_WALLET_NOT_FOUND));
        BigDecimal balanceBefore = coinWallet.getQuantity();
        BigDecimal updatedQuantity = balanceBefore.subtract(request.getQuantity());
        if (updatedQuantity.compareTo(BigDecimal.ZERO) < 0) {
            return false;
        }
        coinWallet.coinWalletUpdate(updatedQuantity);
        coinWalletRepository.save(coinWallet);
        // CoinHistory 저장
//        coinHistoryService.createCoinHistory(request.getUsername(), request.getCoin_id(), request.getQuantity(),
//            balanceBefore, updatedQuantity, "코인 바인딩");
        saveCoinHistoryAsync(request.getUsername(), request.getCoin_id(), request.getQuantity(),
            balanceBefore, updatedQuantity, "코인 바인딩");
        return true;
    }

    @Transactional(readOnly = true) //유저의 로그인 ID에 해당하는 코인지갑 확인
    public List<CoinWalletVO> getCoinWallet(String username) {
        List<CoinWallet> coinWallet = coinWalletRepository.findByUsername(username);
        return coinWallet.stream()
            .map(CoinWallet::toCoinWalletVO)
            .collect(Collectors.toList());
    }

    @Transactional //메시징 시스템을 통해 새로운 입찰이 나타났을때 기존 입찰에 대한 코인 회복 -> 코인 히스토리 생성
    public void restoreCoins(String username, Long coinId, BigDecimal amount) {
        CoinWallet wallet = coinWalletRepository.findByUsernameAndCoinId(username, coinId)
            .orElseThrow(() -> new GlobalException(ResultCase.COIN_WALLET_NOT_FOUND));
        System.out.println("coin amount : " + amount);
        BigDecimal amount1 = amount;
        BigDecimal balanceBefore = wallet.getQuantity(); // 변경 전 잔액
        BigDecimal balanceAfter = balanceBefore.add(amount1); // 변경 후 잔액
        wallet.coinWalletUpdate(balanceAfter);
        coinWalletRepository.save(wallet);
        //코인 히스토리 생성
//        coinHistoryService.createCoinHistory(username, coinId, amount1, balanceBefore, balanceAfter, "기존 입찰 코인 회복");
        saveCoinHistoryAsync(username, coinId, amount1, balanceBefore, balanceAfter, "기존 입찰 코인 회복");

    }

    public void recoverBidCoin(CoinBidRequest request) {
        CoinWallet coinWallet = coinWalletRepository.findByUsernameAndCoinId(request.getUsername(),
                request.getCoin_id())
            .orElseThrow(() -> new GlobalException(ResultCase.COIN_WALLET_NOT_FOUND));
//        if (coinWallet == null) {
//            throw new GlobalException(ResultCase.COIN_WALLET_NOT_FOUND);
//        }
        BigDecimal balanceBefore = coinWallet.getQuantity();
        BigDecimal updatedQuantity = balanceBefore.add(request.getQuantity());
        coinWallet.coinWalletUpdate(updatedQuantity);
        coinWalletRepository.save(coinWallet);
        // CoinHistory 저장
        coinHistoryService.createCoinHistory(request.getUsername(), request.getCoin_id(), request.getQuantity(),
            balanceBefore, updatedQuantity, "코인 회복");
//        saveCoinHistoryAsync(request.getUsername(), request.getCoin_id(), request.getQuantity(),
//            balanceBefore, updatedQuantity, "코인 회복");
    }

    @Transactional
    public void exchangeCoin(CoinExchangeRequest request, String username) {
        // 코인1 (bf_coin_id)의 월렛 조회
        CoinWallet bfCoinWallet = coinWalletRepository.findByUsernameAndCoinId(username,
                request.getBf_coin_id())
            .orElseThrow(() -> new GlobalException(ResultCase.COIN_WALLET_NOT_FOUND));

        BigDecimal bfCoinQuantity = bfCoinWallet.getQuantity(); // 코인1 수량

        if (bfCoinQuantity.compareTo(BigDecimal.ZERO) <= 0) {
            throw new GlobalException(ResultCase.INSUFFICIENT_COIN_QUANTITY);
        }

        // 코인1 (bf_coin_id)와 코인2 (at_coin_id)의 가격 조회
        Coin bfCoin = coinRepository.findByIdAndIsDeletedFalse(request.getBf_coin_id())
            .orElseThrow(() -> new GlobalException(ResultCase.COIN_NOT_FOUND));
        Coin atCoin = coinRepository.findByIdAndIsDeletedFalse(request.getAt_coin_id())
            .orElseThrow(() -> new GlobalException(ResultCase.COIN_NOT_FOUND));

        BigDecimal bfCoinPrice = bfCoin.getCoinPrice(); // 코인1 가격
        BigDecimal atCoinPrice = atCoin.getCoinPrice(); // 코인2 가격

        // 교환될 코인 수량 계산 (코인1 수량 * 코인1 가격 / 코인2 가격)
        BigDecimal exchangeQuantity = bfCoinQuantity.multiply(bfCoinPrice).divide(atCoinPrice, 2, RoundingMode.HALF_UP);

        // 코인2 (at_coin_id)에 해당하는 지갑 조회 또는 생성
        CoinWallet atCoinWallet = coinWalletRepository.findByUsernameAndCoinId(username, request.getAt_coin_id())
            .orElseGet(() -> CoinWallet.builder()
                .username(username)
                .coinId(request.getAt_coin_id())
                .quantity(BigDecimal.ZERO)
                .build());

        // 코인2 (at_coin_id)의 지갑에 수량 업데이트
        BigDecimal atCoinBalanceBefore = atCoinWallet.getQuantity();
        BigDecimal atCoinBalanceAfter = atCoinBalanceBefore.add(exchangeQuantity);
        atCoinWallet.coinWalletUpdate(atCoinBalanceAfter);

        // 코인1의 수량 차감
        bfCoinWallet.coinWalletUpdate(BigDecimal.ZERO); // 코인1 전량 사용

        // DB 저장
        coinWalletRepository.save(atCoinWallet);
        coinWalletRepository.save(bfCoinWallet);

        // 코인 히스토리 비동기 처리 (코인1 차감, 코인2 추가)
        saveCoinHistoryAsync(username, request.getBf_coin_id(), bfCoinQuantity, bfCoinQuantity, BigDecimal.ZERO, "코인 교환");
        saveCoinHistoryAsync(username, request.getAt_coin_id(), exchangeQuantity, atCoinBalanceBefore, atCoinBalanceAfter, "코인 교환");

        log.info("Coin exchange completed for user: {} ({} -> {})", username, request.getBf_coin_id(), request.getAt_coin_id());
    }

}
