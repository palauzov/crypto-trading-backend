package com.example.crypto_trading.controller;

import com.example.crypto_trading.model.Coin;
import com.example.crypto_trading.model.User;
import com.example.crypto_trading.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        if (username == null || username.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Username is required");
        }
        User user = new User(username, 10000);
        userRepository.save(user);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/reset/{username}")
    public void resetBalance(@PathVariable String username) {
        User user = userRepository.getUserByUsername(username).orElseThrow();
        user.setCoins(null);
        user.setFiatAmount(10000);
        user.setTransactions(null);
        userRepository.saveAndFlush(user);
    }

    @GetMapping("/balance/{username}")
    public ResponseEntity<Double> getBalance(@PathVariable String username) {
        User user = userRepository.getUserByUsername(username).orElseThrow();
        return ResponseEntity.ok(user.getFiatAmount());
    }

    @GetMapping("/cryptos/{username}")
    public ResponseEntity<List<Coin>>  getCryptoBalance(@PathVariable String username) {
        User user = userRepository.getUserByUsername(username).orElseThrow();
        return ResponseEntity.ok(user.getCoins());
    }

}
