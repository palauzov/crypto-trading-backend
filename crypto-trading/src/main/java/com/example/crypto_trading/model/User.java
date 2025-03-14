package com.example.crypto_trading.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class User extends BaseEntity{

    @Column(nullable = false)
    private String username;
    @Column
    private double fiatAmount;
    @ManyToMany
    private List<Coin> coins;
    @OneToMany
    private List<Transaction> transactions;

    public User(String username, double fiatAmount) {
        this.username = username;
        this.fiatAmount = fiatAmount;
        this.coins = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getFiatAmount() {
        return fiatAmount;
    }

    public void setFiatAmount(double fiatAmount) {
        this.fiatAmount = fiatAmount;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
