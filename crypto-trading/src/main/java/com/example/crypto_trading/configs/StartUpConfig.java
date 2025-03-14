package com.example.crypto_trading.configs;

import com.example.crypto_trading.model.User;
import com.example.crypto_trading.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StartUpConfig {
    @Autowired
    private UserRepository userRepository;

    @Value("${app.user.username:}")
    private String username;

    @Bean
    public ApplicationRunner init() {
        return args -> {
            if (userRepository.count() == 0) {
                User defaultUser = new User();
                defaultUser.setUsername(username);
                defaultUser.setFiatAmount(10000);
                userRepository.save(defaultUser);
            } else {
                System.out.println("User already exist, skipping creation.");
            }
        };
    }
}
