package com.nameslowly.coinauctions.bidwin.presentation.controller;

import com.nameslowly.coinauctions.bidwin.application.service.BidService;
import com.nameslowly.coinauctions.bidwin.presentation.dto.request.RegisterBidRequest;
import com.nameslowly.coinauctions.bidwin.presentation.dto.response.RegisterBidResponse;
import com.nameslowly.coinauctions.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BidController {

    private final BidService bidService;

    @PostMapping("/api/bids")
    public CommonResponse<RegisterBidResponse> register(@RequestBody RegisterBidRequest request) {
        Long bidId = bidService.register(request.toDto());
        return CommonResponse.success(new RegisterBidResponse(bidId));
    }

}
