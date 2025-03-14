package com.example.crypto_trading.services;

import com.example.crypto_trading.model.Transaction;
import com.example.crypto_trading.model.User;
import com.example.crypto_trading.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private UserRepository userRepository;

    public TransactionService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<Transaction> getTransactionsByUsername(String username) {
        User user = userRepository.getUserByUsername(username).orElseThrow();
        return user.getTransactions();
    }
}
