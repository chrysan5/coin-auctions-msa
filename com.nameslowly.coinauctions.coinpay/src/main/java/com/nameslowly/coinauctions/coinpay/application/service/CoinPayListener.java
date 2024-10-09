package com.nameslowly.coinauctions.coinpay.application.service;

import com.nameslowly.coinauctions.coinpay.application.dto.response.BidRefundMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoinPayListener {
    private final CoinWalletService coinWalletService;

    @RabbitListener(queues = "${message.queue.bid-cancel}")
    public void handleBidRefund(BidRefundMessage bidRefundMessage) {
        // 메시지에 담긴 정보로 유저의 코인 복구
        coinWalletService.restoreCoins(bidRefundMessage.getUsername(), bidRefundMessage.getCoin_id(), bidRefundMessage.getAmount());
    }


}
