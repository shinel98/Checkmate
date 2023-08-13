package com.likelion.checkmate;

import com.likelion.checkmate.jwt.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class CheckmateApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckmateApplication.class, args);
	}

}
