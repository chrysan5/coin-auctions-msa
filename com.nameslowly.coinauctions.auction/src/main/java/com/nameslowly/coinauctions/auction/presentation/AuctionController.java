package com.nameslowly.coinauctions.auction.presentation;

import com.nameslowly.coinauctions.auction.application.AuctionService;
import com.nameslowly.coinauctions.auction.domain.model.Auction;
import com.nameslowly.coinauctions.auction.presentation.dto.request.RegisterAuctionRequest;
import com.nameslowly.coinauctions.auction.presentation.dto.response.RegisterAuctionResponse;
import com.nameslowly.coinauctions.common.response.CommonResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuctionController {

    private final AuctionService auctionService;

    @PostMapping("/api/auctions")
    public CommonResponse<RegisterAuctionResponse> register(
        @RequestBody RegisterAuctionRequest request) {
        Long auctionId = auctionService.register(request.toDto());
        return CommonResponse.success(new RegisterAuctionResponse(auctionId));
    }
}
