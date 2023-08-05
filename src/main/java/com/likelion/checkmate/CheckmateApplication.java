package com.likelion.checkmate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CheckmateApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckmateApplication.class, args);
	}

}
