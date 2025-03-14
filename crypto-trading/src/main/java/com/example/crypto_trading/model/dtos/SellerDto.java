package com.example.crypto_trading.model.dtos;

public class SellerDto {

    private String username;
    private String crypto;
    private double usdAmount;
    private double cryptoAmount;

    public SellerDto(String username, String crypto, double usdAmount, double cryptoAmount) {
        this.username = username;
        this.crypto = crypto;
        this.usdAmount = usdAmount;
        this.cryptoAmount = cryptoAmount;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCrypto() {
        return crypto;
    }

    public void setCrypto(String crypto) {
        this.crypto = crypto;
    }

    public double getUsdAmount() {
        return usdAmount;
    }

    public void setUsdAmount(double usdAmount) {
        this.usdAmount = usdAmount;
    }

    public double getCryptoAmount() {
        return cryptoAmount;
    }

    public void setCryptoAmount(double cryptoAmount) {
        this.cryptoAmount = cryptoAmount;
    }
}
