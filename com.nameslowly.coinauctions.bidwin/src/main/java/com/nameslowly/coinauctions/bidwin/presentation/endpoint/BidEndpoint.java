package com.nameslowly.coinauctions.bidwin.presentation.endpoint;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BidEndpoint {

    private final RabbitTemplate rabbitTemplate;

//    @RabbitListener(queues = "${message.queue.coinpay}")
//    public void test(Bid message) {
//        log.info("message = {}", message);
//    }

}
