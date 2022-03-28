package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {

    private long accountId;
    private long userId;
    private BigDecimal balance;

    //Getters
    public long getAccountId() {
        return accountId;
    }
    public long getUserId() {
        return userId;
    }
    public BigDecimal getBalance() {
        return balance;
    }

    //Setters
    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
