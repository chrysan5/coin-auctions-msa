package com.nameslowly.coinauctions.coinpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.nameslowly.coinauctions.coinpay","com.nameslowly.coinauctions.common"})
public class CoinpayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoinpayApplication.class, args);
	}

}
