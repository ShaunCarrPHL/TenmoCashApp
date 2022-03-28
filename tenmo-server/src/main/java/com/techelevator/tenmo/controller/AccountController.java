package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;

import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;


@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {

    private AccountDao account;
    private UserDao user;

    public AccountController(AccountDao account, UserDao user) {
        this.account = account;
        this.user = user;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(path = "balance/{id}", method = RequestMethod.GET)
    public BigDecimal getBalance (@PathVariable Long id)
    {
        BigDecimal balance = account.getBalance(id);
        return balance;
    }







}
