package com.nameslowly.coinauctions.bidwin.infrastructure;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Value("${message.exchange}")
    private String exchange;
    @Value("${message.queue.coinpay}")
    private String queueCoinpay;

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Queue queueProduct() {
        return new Queue(queueCoinpay);
    }

    @Bean
    public Binding bindingProduct() {
        return BindingBuilder.bind(queueProduct()).to(exchange()).with(queueCoinpay);
    }

}
