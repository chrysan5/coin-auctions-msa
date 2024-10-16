package com.nameslowly.coinauctions.chat.infrastructure;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ChatApplicationQueueConfig {

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Value("${message.exchange}")
    private String exchange;

    @Value("${message.queue.auction-info}")
    private String queueAuctionInfo;


    @Bean public TopicExchange exchange() { return new TopicExchange(exchange); }

    @Bean public Queue queueAuctionInfo() { return new Queue(queueAuctionInfo); }

    @Bean public Binding bindingAuctionInfo() { return BindingBuilder.bind(queueAuctionInfo()).to(exchange()).with(queueAuctionInfo); }

}
