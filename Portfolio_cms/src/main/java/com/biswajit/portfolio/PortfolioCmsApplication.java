package com.biswajit.portfolio;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PortfolioCmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioCmsApplication.class, args);
	}
	
	@Bean
	public LocalDateTime getTime()
	{
		return LocalDateTime.now();
	}

}
