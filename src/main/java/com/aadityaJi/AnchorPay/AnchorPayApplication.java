package com.aadityaJi.AnchorPay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class AnchorPayApplication {

	public static void main(String[] args) {

		SpringApplication.run(AnchorPayApplication.class, args);
	}

}
