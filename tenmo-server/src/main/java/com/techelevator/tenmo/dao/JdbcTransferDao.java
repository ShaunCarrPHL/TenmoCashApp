package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {

    private JdbcTemplate jdbcTemplate;
    private AccountDao accountDao;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate, AccountDao accountDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.accountDao = accountDao;
    }

    public List<Transfer> getALLTransfers(long userId) {
        ArrayList<Transfer> transfers = new ArrayList<>();
        String sql = "select transfer_id, transfer_type.transfer_type_desc, transfer_status.transfer_status_desc, uf.username as user_from, uto.username as user_to, amount" +
                "                FROM transfer" +
                "                JOIN transfer_status" +
                "                ON transfer_status.transfer_status_id = transfer.transfer_status_id" +
                "                JOIN transfer_type" +
                "                ON transfer_type.transfer_type_id = transfer.transfer_type_id" +
                "                JOIN account af on af.account_id = transfer.account_from" +
                "                JOIN account ato on ato.account_id = transfer.account_to" +
                "                JOIN tenmo_user uf on uf.user_id = af.user_id" +
                "                JOIN tenmo_user uto on uto.user_id = ato.user_id " +
                "                WHERE uf.user_id = ? or uto.user_id = ?";

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId, userId);
            while (result.next()) {
                Transfer transfer = mapRowToTransferList(result);
                transfers.add(transfer);
            }
            return transfers;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Transfer getTransferWithId(long transferId) {
        Transfer transfer = new Transfer();
        String sql = "SELECT transfer_id, transfer_type.transfer_type_desc, transfer_status.transfer_status_desc, uf.username as user_from, uto.username as user_to, amount" +
                "                FROM transfer" +
                "                JOIN transfer_status" +
                "                ON transfer_status.transfer_status_id = transfer.transfer_status_id" +
                "                JOIN transfer_type" +
                "                ON transfer_type.transfer_type_id = transfer.transfer_type_id" +
                "                JOIN account af on af.account_id = transfer.account_from" +
                "                JOIN account ato on ato.account_id = transfer.account_to" +
                "                JOIN tenmo_user uf on uf.user_id = af.user_id" +
                "                JOIN tenmo_user uto on uto.user_id = ato.user_id" +
                "                WHERE transfer_id = ?;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, transferId);
            while (result.next()) {
                transfer = mapRowToTransfer(result);

            }
            return transfer;
        } catch (ResourceAccessException rae) {
            System.out.println("Server Error : Cannot Connect");
        }
        return transfer;
    }

    public String sendTransfer(long userFrom, long userTo, BigDecimal transferAmount) {
        if (userFrom == userTo) {
            return "Cannot send money to yourself, nice try!";
        }
        if (transferAmount.compareTo(new BigDecimal(0)) == 0) {
            return "Cannot send an empty transfer.";
        }
        try {
            if (transferAmount.compareTo(accountDao.getBalance(userFrom)) == -1 || transferAmount.compareTo(accountDao.getBalance(userFrom)) == 0) {
                String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                        "VALUES (2,2,?,?,?);";
                jdbcTemplate.update(sql, accountDao.findUserWithId(userFrom).getAccountId(),
                        accountDao.findUserWithId(userTo).getAccountId(), transferAmount);
                accountDao.subtractBalance(transferAmount, accountDao.findUserWithId(userFrom).getAccountId());
                accountDao.addBalance(transferAmount, accountDao.findUserWithId(userTo).getAccountId());
                return "Transfer complete!";
            } else
                return "Cannot complete transfer, check transfer amount and receiving user.";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public String requestTransfer (long userFrom, long userTo, BigDecimal transferAmount) {
        return null;
    }
    public List<Transfer> getPendingRequest(long userid) {
        ArrayList<Transfer> pendingRequest = new ArrayList<>();
        return null;
    }
    public String updateTransferRequest (Transfer transfer, long statusId) {
        return null;
    }

 private Transfer mapRowToTransfer(SqlRowSet results) {
     Transfer transfer = new Transfer();
     transfer.setTransferId(results.getLong("transfer_id"));
     transfer.setTransferTypeDesc(results.getString("transfer_type_desc"));
     transfer.setTransferStatusDesc(results.getString("transfer_status_desc"));
     transfer.setUserTo(results.getString("user_to"));
     transfer.setUserFrom(results.getString("user_from"));
     transfer.setAmount(results.getBigDecimal("amount"));
     return transfer;
 }
    private Transfer mapRowToTransferList(SqlRowSet results) {
        Transfer transfer = new Transfer();
        transfer.setAmount(results.getBigDecimal("amount"));
        transfer.setTransferId(results.getLong("transfer_id"));
        transfer.setUserFrom(results.getString("user_from"));
        transfer.setUserTo(results.getString("user_to"));
        transfer.setTransferTypeDesc(results.getString("transfer_type_desc"));
        transfer.setTransferStatusDesc(results.getString("transfer_status_desc"));
        return transfer;
    }

}
