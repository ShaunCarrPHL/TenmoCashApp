package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {

    private long transferId;
    private long transferTypeId;
    private long transferStatusId;
    private long accountFrom;
    private long accountTo;
    private BigDecimal amount;
    private String transferTypeDesc;
    private String transferStatusDesc;
    private String userFrom;
    private String userTo;


    //Getters
    public long getTransferId() {
        return transferId;
    }
    public long getTransferTypeId() {
        return transferTypeId;
    }
    public long getTransferStatusId() {
        return transferStatusId;
    }

    public long getAccountFrom() {
        return accountFrom;
    }

    public long getAccountTo() {
        return accountTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getTransferTypeDesc() {
        return transferTypeDesc;
    }
    public String getTransferStatusDesc() {
        return transferStatusDesc;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public String getUserTo() {
        return userTo;
    }


    //Setters
    public void setTransferId(long transferId) {
        this.transferId = transferId;
    }

    public void setTransferTypeId(long transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public void setTransferStatusId(long transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public void setAccountFrom(long accountFrom) {
        this.accountFrom = accountFrom;
    }

    public void setAccountTo(long accountTo) {
        this.accountTo = accountTo;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setTransferTypeDesc(String transferTypeDesc) {
        this.transferTypeDesc = transferTypeDesc;
    }


    public void setTransferStatusDesc(String transferStatusDesc) {
        this.transferStatusDesc = transferStatusDesc;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    public void setUserTo(String userTo) {
        this.userTo = userTo;
    }
}
