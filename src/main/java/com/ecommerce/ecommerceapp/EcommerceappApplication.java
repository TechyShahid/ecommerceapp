package com.ecommerce.ecommerceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class EcommerceappApplication {

	public static void main(String[] args) {
		//entry point
		SpringApplication.run(EcommerceappApplication.class, args);
	}

}
