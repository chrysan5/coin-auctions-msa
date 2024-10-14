package com.nameslowly.coinauctions.bidwin.presentation;

import com.nameslowly.coinauctions.bidwin.application.BidService;
import com.nameslowly.coinauctions.bidwin.domain.model.Bid;
import com.nameslowly.coinauctions.bidwin.infrastructure.coinpay.CoinpayService;
import com.nameslowly.coinauctions.bidwin.presentation.request.RegisterBidRequest;
import com.nameslowly.coinauctions.bidwin.presentation.response.RegisterBidResponse;
import com.nameslowly.coinauctions.bidwin.presentation.response.RetrieveBidPageResponse;
import com.nameslowly.coinauctions.bidwin.presentation.response.RetrieveBidResponse;
import com.nameslowly.coinauctions.common.response.CommonResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/api/bids")
    public CommonResponse<List<RetrieveBidPageResponse>> retrieveBidPage(Pageable page) {
        List<Bid> bidPage = bidService.retrieveBidPage(page);
        List<RetrieveBidPageResponse> response = bidPage.stream().map(RetrieveBidPageResponse::of).toList();
        return CommonResponse.success(response);
    }

    @GetMapping("/api/bids/{bidId}")
    public CommonResponse<RetrieveBidResponse> retrieveBid(@PathVariable("bidId") Long bidId) {
        Bid bid = bidService.retrieveBid(bidId);
        RetrieveBidResponse response = RetrieveBidResponse.of(bid);
        return CommonResponse.success(response);
    }
}
