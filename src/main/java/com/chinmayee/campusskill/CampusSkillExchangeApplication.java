package com.chinmayee.campusskill;

import javax.crypto.SecretKey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.jsonwebtoken.Jwts;

@SpringBootApplication
public class CampusSkillExchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampusSkillExchangeApplication.class, args);
	}

}
