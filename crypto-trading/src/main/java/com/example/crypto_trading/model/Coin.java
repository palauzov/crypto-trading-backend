package com.example.crypto_trading.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class Coin extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String pair;

    @Column(nullable = false)
    private double amount;

    public Coin(String name, String pair, double amount) {
        this.name = name;
        this.pair = pair;
        this.amount = amount;
    }

    public Coin() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
