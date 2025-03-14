package com.example.crypto_trading.controller;

import com.example.crypto_trading.model.*;
import com.example.crypto_trading.model.dtos.BuyerDto;
import com.example.crypto_trading.model.dtos.SellerDto;
import com.example.crypto_trading.repos.CoinRepository;
import com.example.crypto_trading.repos.TransactionRepository;
import com.example.crypto_trading.repos.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/cryptos")
public class CoinController {

    @Autowired
    CoinRepository coinRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @Transactional
    @PostMapping("/buy")
    public ResponseEntity<?> buyCrypto(@RequestBody BuyerDto buyerDto) {
        User user = userRepository.getUserByUsername(buyerDto.getUsername()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        if (buyerDto.getUsdAmount() < user.getFiatAmount()) {
            String cryptoName = buyerDto.getCrypto().substring(0, 3);
            Coin coin;
            if (coinRepository.getCoinByName(cryptoName).isPresent()) {
                coin = coinRepository.getCoinByName(cryptoName).get();
                user.getCoins().stream()
                        .filter(c -> c.getName().equals(coin.getName()))
                        .findFirst().orElseThrow()
                        .setAmount(coin.getAmount() + buyerDto.getCryptoAmount());
            } else {
                coin = new Coin(cryptoName, buyerDto.getCrypto(), buyerDto.getCryptoAmount());
                user.getCoins().add(coin);
            }
            coinRepository.save(coin);
            Transaction transaction = new Transaction(LocalDateTime.now(), TransactionType.BUY, coin, buyerDto.getUsdAmount(), buyerDto.getCryptoAmount());
            user.setFiatAmount(user.getFiatAmount() - buyerDto.getUsdAmount());
            user.getTransactions().add(transaction);
            transactionRepository.save(transaction);
            userRepository.save(user);
            return ResponseEntity.ok("Purchase successful.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You don't have enough balance");
    }

    @Transactional
    @PostMapping("/sell")
    public ResponseEntity<?> sellCrypto(@RequestBody SellerDto sellerDto) {
        User user = userRepository.getUserByUsername(sellerDto.getUsername()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        String cryptoName = sellerDto.getCrypto().substring(0, 3);
        Coin coin;
        if (user.getCoins().stream()
                .anyMatch(c -> c.getName().equals(cryptoName))) {
            coin = user.getCoins().stream().filter(c -> c.getName().equals(cryptoName)).findFirst().get();
            if (sellerDto.getCryptoAmount() > coin.getAmount()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You do not have enough crypto assets!");
            } else if (sellerDto.getCryptoAmount() - coin.getAmount() == 0) {
                coinRepository.delete(coin);
            } else {
                coin.setAmount(coin.getAmount() - sellerDto.getCryptoAmount());
                coinRepository.save(coin);
            }
            Transaction transaction = new Transaction(LocalDateTime.now(), TransactionType.SELL, coin, sellerDto.getUsdAmount(), sellerDto.getCryptoAmount());
            transactionRepository.save(transaction);
            user.setFiatAmount(user.getFiatAmount() + sellerDto.getUsdAmount());
            user.getTransactions().add(transaction);
            userRepository.save(user);
            return ResponseEntity.ok("Sale successful!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You do not have " + cryptoName);
        }

    }

}
