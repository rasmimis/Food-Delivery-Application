package com.pkg.food.delivery.system.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FoodDeliverySystemApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(FoodDeliverySystemApplicationStarter.class, args);
	}

	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}

}
