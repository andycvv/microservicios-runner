package com.cibertec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StoreAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreAppApplication.class, args);
	}

}
