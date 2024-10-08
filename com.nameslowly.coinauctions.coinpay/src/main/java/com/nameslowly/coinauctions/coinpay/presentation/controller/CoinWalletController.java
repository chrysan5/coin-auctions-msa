package com.nameslowly.coinauctions.coinpay.presentation.controller;

import com.nameslowly.coinauctions.coinpay.domain.model.CoinWalletVO;
import com.nameslowly.coinauctions.coinpay.application.dto.request.CoinBidRequest;
import com.nameslowly.coinauctions.coinpay.application.dto.request.CoinChargeRequest;
import com.nameslowly.coinauctions.coinpay.application.service.CoinWalletService;
import com.nameslowly.coinauctions.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CoinWalletController {
    private final CoinWalletService coinWalletService;

    @PostMapping("/coin_wallets")
    public CommonResponse chargeCoin(@RequestBody CoinChargeRequest request, @RequestHeader("X-User-Name") String username){
        CoinWalletVO coinWallet = coinWalletService.saveCoinWallet(request, username);
        return CommonResponse.success(coinWallet);
    }
    @PutMapping("/internal/coin_wallets") //해당 api는 feign요청이므로 일단은 CommonResponse를 따로 통일 안함 테스트 우선
    public void changeBidCoin(@RequestBody CoinBidRequest request){
        coinWalletService.changeBidCoin(request);
    }
    @GetMapping("/coin_wallets/{username}")
    public CommonResponse getCoinWallet(@PathVariable String username){
        List<CoinWalletVO> coinWallet = coinWalletService.getCoinWallet(username);
        return CommonResponse.success(coinWallet);
    }

}
