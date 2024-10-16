package com.nameslowly.coinauctions.chat.presentation.endpoint;


import com.nameslowly.coinauctions.chat.application.dto.AuctionInfoMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//테스트용(rabbitmq 프로듀서)
@Slf4j
@RestController
@RequiredArgsConstructor
public class AuctionEndpointTest {

    @Value("${message.queue.auction-info}")
    private String auctionInfoQueue;

    private final RabbitTemplate rabbitTemplate;


    @PostMapping("/api/chat/rabbitmqTest")
    public ResponseEntity<Void> auction(@RequestBody AuctionInfoMessage auctionInfoMessage) {
        //auction 객체 저장 로직 수행
        rabbitTemplate.convertAndSend(auctionInfoQueue, auctionInfoMessage);
        log.info("auction send Message : {}",auctionInfoMessage.toString());
        return ResponseEntity.noContent().build();
    }
}

