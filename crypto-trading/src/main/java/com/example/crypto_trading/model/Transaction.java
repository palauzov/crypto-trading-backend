package com.example.crypto_trading.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class Transaction extends BaseEntity{

    @Column(nullable = false)
    private LocalDateTime issueDate;
    @Column(nullable = false)
    private TransactionType type;
    @ManyToOne
    @JoinColumn(name = "coin_id")
    private Coin coin;
    @Column(nullable = false)
    private double amount;
    @Column(nullable = false)
    private double cryptoAmount;

    public Transaction(LocalDateTime issueDate, TransactionType type, Coin coin, double amount, double cryptoAmount) {
        this.issueDate = issueDate;
        this.type = type;
        this.coin = coin;
        this.amount = amount;
        this.cryptoAmount = cryptoAmount;
    }
    public Transaction() {
    }
    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getCryptoAmount() {
        return cryptoAmount;
    }

    public void setCryptoAmount(double cryptoAmount) {
        this.cryptoAmount = cryptoAmount;
    }
}
