package com.example.afroseats_userService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AfroseatsUserServiceApplication {

	public static void main(String[] args) {
		System.out.println("Database Details: " + System.getenv("EXTERNAL_CONFIG_FILE_PATH"));
		SpringApplication.run(AfroseatsUserServiceApplication.class, args);
	}

}
