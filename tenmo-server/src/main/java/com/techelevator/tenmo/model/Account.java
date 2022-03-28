package com.techelevator.tenmo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class Account {
    private Long accountId;
    @NotBlank( message = "The userId field is required.")
    private Long userid;
    @Positive(message = "The balance cannot be negative")
    private BigDecimal balance;

    public Account () {

    }

    public Account(Long accountId, Long userid, BigDecimal balance) {
        this.accountId = accountId;
        this.userid = userid;
        this.balance = balance;
    }

    //Getters
    public Long getAccountId() {
        return accountId;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public Long getUserid() {
        return userid;
    }

    //Setters
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    public void setUserid(Long accountId) {
        this.userid = userid;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;

    }


}
