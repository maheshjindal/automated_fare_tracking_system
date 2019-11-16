package com.pirates.frts.domain;

public class CardInformation {
    private String userId;
    private String rfId;
    private double cardLimit;
    private double transactionAmount;
    private double balance;

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setRfId(String rfId) {
        this.rfId = rfId;
    }

    public void setCardLimit(double cardLimit) {
        this.cardLimit = cardLimit;
    }

    public String getUserId() {
        return userId;
    }

    public String getRfId() {
        return rfId;
    }

    public double getCardLimit() {
        return cardLimit;
    }
}
