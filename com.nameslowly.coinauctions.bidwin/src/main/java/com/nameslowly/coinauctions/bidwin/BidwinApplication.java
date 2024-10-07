package com.nameslowly.coinauctions.bidwin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@ComponentScan(basePackages = {"com.nameslowly.coinauctions.bidwin",
    "com.nameslowly.coinauctions.common"})
@SpringBootApplication
public class BidwinApplication {

    public static void main(String[] args) {
        SpringApplication.run(BidwinApplication.class, args);
    }

}
