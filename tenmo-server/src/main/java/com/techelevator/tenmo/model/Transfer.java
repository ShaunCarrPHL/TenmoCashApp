package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {

    private Long transferId;
    private Long accountFrom;
    private Long accountTo;
    private BigDecimal amount;
    private Long transferTypeId;
    private Long transferStatusId;
    private String transferTypeDesc;
    private String transferStatusDesc;

    private String userFrom;
    private String userTo;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    private String sender;
    private String receiver;

    public Transfer() {

    }
    //Getters
    public Long getTransferId() {
        return transferId;
    }

    public Long getAccountFrom() {
        return accountFrom;
    }

    public Long getAccountTo() {
        return accountTo;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    public Long getTransferTypeId() {
        return transferTypeId;
    }

    public Long getTransferStatusId() {
        return transferStatusId;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public String getUserTo() {
        return userTo;
    }

    public String getTransferTypeDesc() {
        return transferTypeDesc;
    }

    public String getTransferStatusDesc() {
        return transferStatusDesc;
    }

    //Setters
    public void setTransferId(Long transferId) {
        this.transferId = transferId;
    }

    public void setAccountFrom(Long accountFrom) {
        this.accountFrom = accountFrom;
    }

    public void setAccountTo(Long accountTo) {
        this.accountTo = accountTo;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setTransferTypeId(long transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public void setTransferStatusId(long transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public void setUserFrom(String userFrom) {this.userFrom = userFrom;}

    public void setTransferTypeDesc(String transferTypeDesc) {
        this.transferTypeDesc = transferTypeDesc;
    }

    public void setTransferStatusDesc(String transferStatusDesc) {
        this.transferStatusDesc = transferStatusDesc;
    }

    public void setUserTo(String userTo) {
        this.userTo = userTo;


    }
}
