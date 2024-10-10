package com.nameslowly.coinauctions.bidwin.application;

import com.nameslowly.coinauctions.bidwin.application.dto.RegisterBidDto;
import com.nameslowly.coinauctions.bidwin.domain.model.Bid;
import com.nameslowly.coinauctions.bidwin.domain.service.BidFactory;
import com.nameslowly.coinauctions.bidwin.domain.service.BidReader;
import com.nameslowly.coinauctions.bidwin.infrastructure.auction.AuctionDto;
import com.nameslowly.coinauctions.bidwin.infrastructure.auction.AuctionService;
import com.nameslowly.coinauctions.bidwin.infrastructure.coinpay.CoinBidRequest;
import com.nameslowly.coinauctions.bidwin.infrastructure.coinpay.CoinDto;
import com.nameslowly.coinauctions.bidwin.infrastructure.coinpay.CoinpayService;
import com.nameslowly.coinauctions.bidwin.infrastructure.message.BidCancelMessage;
import com.nameslowly.coinauctions.bidwin.infrastructure.message.BidRegisterMessage;
import com.nameslowly.coinauctions.bidwin.infrastructure.user.UserService;
import com.nameslowly.coinauctions.common.exception.GlobalException;
import com.nameslowly.coinauctions.common.response.ResultCase;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Value("${message.queue.bid-cancel}")
    private String queueBidCancel;
    @Value("${message.queue.bid-register}")
    private String queueBidRegister;

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
//        UserDto user = userService.getUser(dto.getParticipantMemberId());
        log.info("입찰자 조회");
        AuctionDto auction = auctionService.getAuction(dto.getAuctionId());
        log.info("경매 조회");
        CoinDto coin = coinpayService.getCoin(dto.getCoinId());
        log.info("코인 조회");

        if (!auction.getAuctionStatus().equals("ONGOING")) {
            log.info("진행 중인 경매 아님");
            throw new GlobalException(ResultCase.NOT_ONGOING_AUCTION);
        }

        Bid newBid;
        Boolean isNewBid = false;
        BidCancelMessage bidCancelMessage = null;
        Optional<Bid> winBidOpt = bidReader.findWinBidByAuctionId(dto.getAuctionId());
        if (winBidOpt.isPresent()) {
            log.info("현재 입찰 존재");
            Bid winBid = winBidOpt.get();

            if (winBid.getParticipantMemberUsername() == dto.getParticipantMemberUsername()) {
                throw new RuntimeException("최고 입찰자가 본인임");
            }

            if (notExceed(dto.getCoinAmount(), winBid.getCoinAmount())) {
                log.info("현재 입찰 보다 적음");
                throw new GlobalException(ResultCase.NOT_ENOUGH_THAN_CURRENT_PRICE);
            }

            bidCancelMessage = new BidCancelMessage(
                winBid.getParticipantMemberUsername(), winBid.getCoinId(),
                winBid.getCoinAmount());
        } else {
            isNewBid = true;
            log.info("최초 입찰");

            if (notExceed(dto.getCoinAmount(), auction.getBasePrice())) {
                log.info("경매 시작가보다 보다 적음");
                throw new GlobalException(ResultCase.NOT_ENOUGH_THAN_BASE_AMOUNT);
            }
        }

        if (!coinpayService.useCoin(
            new CoinBidRequest(dto.getParticipantMemberUsername(), dto.getCoinId(),
                dto.getCoinAmount()))) {
            log.info("새 입찰자 코인 부족");
            throw new GlobalException(ResultCase.NOT_ENOUGH_USER_COIN_AMOUNT);
        }
        log.info("새 입찰자 코인 사용");

        try {
            newBid = bidFactory.create(dto);
            log.info("새 입찰 생성");
        } catch (Exception e) {
            log.info("새 입찰 생성 에러");
            coinpayService.recoverCoin(
                new CoinBidRequest(dto.getParticipantMemberUsername(), dto.getCoinId(),
                    dto.getCoinAmount()));
            log.info("새 입찰자 코인 회복");
            throw new GlobalException(ResultCase.NEW_BID_CREATE_ERROR);
        }

        log.info("새 입찰 등록");

        BidRegisterMessage bidRegisterMessage = new BidRegisterMessage(newBid.getAuctionId(),
            newBid.getCoinAmount());
        rabbitTemplate.convertAndSend(queueBidRegister, bidRegisterMessage);
        log.info("새 입찰 등록 메시지 발행");

        if (!isNewBid) { // 최초 입찰이 아니면
            rabbitTemplate.convertAndSend(queueBidCancel, bidCancelMessage);
            log.info("현재 입찰 취소 메시지 발행");
        }

        return newBid.getId();
    }

    private boolean notExceed(BigDecimal target, BigDecimal source) {
        return target.compareTo(source) <= 0 ? true : false;
    }

    @Transactional(readOnly = true)
    public List<Bid> retrieveBidPage(Pageable page) {
        return bidReader.getBidPage(page);
    }

    @Transactional(readOnly = true)
    public Bid retrieveBid(Long bidId) {
        return bidReader.getBid(bidId);
    }
}
