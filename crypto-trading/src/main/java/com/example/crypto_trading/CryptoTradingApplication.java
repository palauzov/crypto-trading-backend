package com.example.crypto_trading;

import com.example.crypto_trading.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CryptoTradingApplication {


	public static void main(String[] args) {
		SpringApplication.run(CryptoTradingApplication.class, args);
	}

}
