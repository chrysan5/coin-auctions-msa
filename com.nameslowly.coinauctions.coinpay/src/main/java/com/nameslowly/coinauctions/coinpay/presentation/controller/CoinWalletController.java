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
@RequestMapping("/api/coin_wallets")
public class CoinWalletController {
    private final CoinWalletService coinWalletService;

    @PostMapping
    public CommonResponse chargeCoin(@RequestBody CoinChargeRequest request){
        CoinWalletVO coinWallet = coinWalletService.saveCoinWallet(request);
        return CommonResponse.success(coinWallet);
    }
    @PutMapping("/bids") //해당 api는 feign요청이므로 일단은 CommonResponse를 따로 통일 안함 테스트 우선
    public void changeBidCoin(@RequestBody CoinBidRequest request){
        coinWalletService.changeBidCoin(request);
    }
    @GetMapping("/{username}")
    public CommonResponse getCoinWallet(@PathVariable String username){
        List<CoinWalletVO> coinWallet = coinWalletService.getCoinWallet(username);
        return CommonResponse.success(coinWallet);
    }

}
