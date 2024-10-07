package com.nameslowly.coinauctions.bidwin.application.service;

import com.nameslowly.coinauctions.bidwin.application.dto.RegisterBidDto;
import com.nameslowly.coinauctions.bidwin.domain.model.Bid;
import com.nameslowly.coinauctions.bidwin.domain.service.BidFactory;
import com.nameslowly.coinauctions.bidwin.domain.service.BidReader;
import com.nameslowly.coinauctions.bidwin.infrastructure.auction.AuctionDto;
import com.nameslowly.coinauctions.bidwin.infrastructure.auction.AuctionService;
import com.nameslowly.coinauctions.bidwin.infrastructure.coinpay.CoinDto;
import com.nameslowly.coinauctions.bidwin.infrastructure.coinpay.CoinpayService;
import com.nameslowly.coinauctions.bidwin.infrastructure.user.UserDto;
import com.nameslowly.coinauctions.bidwin.infrastructure.user.UserService;
import java.math.BigDecimal;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BidService {

    private final BidReader bidReader;
    private final BidFactory bidFactory;

    private final UserService userService;
    private final AuctionService auctionService;
    private final CoinpayService coinpayService;

    private final RabbitTemplate rabbitTemplate;
    @Value("${message.queue.bid}")
    private String queueBidCancel;

    /***
     * 1. participant member exist // not -> exception
     * 2. auction exist // not -> exception
     *      2.1 auction status ongoing check
     * 3. coin exist // not -> exception
     *      4.1 win bid exist
     *          5.1 win bid coin amount < new bid coin amount
     *              6. new bider coin decrease // after if error -> exception, new bider coin increase (rollback)
     *                   7. create new bid // exception -> new bider coin increase
     *                      8.1 cancel win bid (event)
     *                          9.1 win bid status change (WIN -> CANCEL)
     *                          9.2 win bider coin increase and member coin update (event)
     *                              10. win bider coin history record // if error -> retry
     *                      8.2 chatting create (event)
     *          5.2 win bid coin amount > new bid coin amount -> exception
     *      4.2 not
     *          5.1 auction base coin amount < new bid coin amount
     *              6. create new bid
     *              7. chatting create (event)
     *          5.2 auction base coin amount > new bid coin amount -> exception
     */
    public Long register(RegisterBidDto dto) {
        UserDto user = userService.getUser(dto.getParticipantMemberId());
        log.info("새 입찰자 존재 확인");
        AuctionDto auction = auctionService.getAuction(dto.getAuctionId());
        BigDecimal baseAmount = auction.getBaseAmount();
        log.info("입찰할 경매 존재 확인 및 시작가 조회");
        CoinDto coin = coinpayService.getCoin(dto.getCoinId());
        log.info("입찰에 쓸 코인 존재 확인");

        Bid newBid;
        BigDecimal newBidCoinAmount = dto.getCoinAmount();

        Optional<Bid> winBidOpt = bidReader.findWinBidByAuctionId(dto.getAuctionId());
        log.info("기존 입찰 존재 확인");
        if (winBidOpt.isPresent()) {
            log.info("기존 입찰 존재");
            Bid winBid = winBidOpt.get();
            if (isNotExceed(newBidCoinAmount, winBid.getCoinAmount())) {
                log.info("기존 입찰 보다 적음");
                throw new RuntimeException("기존 입찰가보다 적습니다");
            }
            coinpayService.decreaseUserCoin(winBid.getParticipantMemberId(),
                winBid.getCoinAmount());
            log.info("새 입찰자 코인 사용");
            try {
                newBid = bidFactory.create(dto);
                log.info("새 입찰  생성");
            } catch (Exception e) {
                log.info("새 입찰 생성 에러");
                coinpayService.increaseUserCoin(winBid.getParticipantMemberId(),
                    winBid.getCoinAmount());
                log.info("새 입찰자 코인 회복");
                throw new RuntimeException("새 입찰 생성 에러 발생했습니다");
            }
            rabbitTemplate.convertAndSend(queueBidCancel, "current bid cancel");
            log.info("기존 입찰 취소 메시지 전송");
        } else {
            log.info("기존 입찰 없음");
            if (isNotExceed(newBidCoinAmount, baseAmount)) {
                log.info("경매 시작가보다 보다 적음");
                throw new RuntimeException("경매 시작가 보다 작습니다");
            }
            newBid = bidFactory.create(dto);
            log.info("새 입찰 생성");
        }
        log.info("새 입찰 등록 완료");
        return newBid.getId();
    }

    private boolean isNotExceed(BigDecimal target, BigDecimal source) {
        return target.compareTo(source) <= 0;
    }

}
