package com.pirates.frts.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/***
 *
 * This stores the information about the travel card
 * userId: corresponds to the unique id of the user
 * rfId : corresponds to the unique id of the card
 * cardLimit: represents the max card limit
 * balance : represents the balance amount left in the card
 * **/
@JsonAutoDetect
public class CardInformation {
    private String userId;
    private String rfId;
    private double cardLimit;
    private double balance;

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
