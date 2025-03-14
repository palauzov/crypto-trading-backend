package com.example.crypto_trading.controller;

import com.example.crypto_trading.model.Transaction;
import com.example.crypto_trading.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions/{username}")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable String username) {
        List<Transaction> transactions = transactionService.getTransactionsByUsername(username);
        return ResponseEntity.ok(transactions);
    }
}
