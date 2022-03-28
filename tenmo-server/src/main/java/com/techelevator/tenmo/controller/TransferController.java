package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@PreAuthorize("isAuthenticated()")
public class TransferController {

    @Autowired
    private TransferDao transferDao;
    @Autowired
    private UserDao userDao;

    public TransferController(TransferDao transferDao, UserDao userDao) {
        this.transferDao = transferDao;
        this.userDao = userDao;
    }
    @ResponseStatus(value = HttpStatus.OK)
    // figure out this path
    @RequestMapping(value = "transfer/{id}/list", method = RequestMethod.GET)
    public List<Transfer> getAllMyTransfers(@PathVariable long id) {
        List<Transfer> allTransfers = transferDao.getALLTransfers(id);
        return allTransfers;
    }
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "transfer/{transferId}", method = RequestMethod.GET)
    public Transfer getTransferById(@PathVariable long transferId) {
        Transfer transfer = transferDao.getTransferWithId(transferId);
        return transfer;

    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(path = "transfer", method = RequestMethod.POST)
    public String sendTransferRequest(@RequestBody Transfer transfer) {
        String request = transferDao.sendTransfer(transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());
        return request;

    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(path = "findall", method = RequestMethod.GET)
    public List<User> returnUsers(){
        List<User> users  = userDao.findAll();
        return users;

    }



}
