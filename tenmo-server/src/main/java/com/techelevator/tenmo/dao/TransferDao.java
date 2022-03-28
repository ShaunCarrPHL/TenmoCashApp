package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import  java.math.BigDecimal;
import java.util.List;

public interface TransferDao {

    public List<Transfer> getALLTransfers (long userId);
    public Transfer getTransferWithId (long transferId);
    public String sendTransfer (long userFrom,long userTo, BigDecimal transferAmount);
    public String requestTransfer (long userFrom, long userTo, BigDecimal transferAmount);
    public List<Transfer> getPendingRequest (long userid);
    public String updateTransferRequest (Transfer transfer, long statusId);








}
