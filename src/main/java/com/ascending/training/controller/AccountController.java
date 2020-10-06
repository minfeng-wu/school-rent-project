package com.ascending.training.controller;


import com.ascending.training.model.Account;
import com.ascending.training.model.School;
import com.ascending.training.service.AccountDaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/account")
public class AccountController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AccountDaoService accountDaoService;

    @GetMapping(value = "", params={"name"},produces = MediaType.APPLICATION_JSON_VALUE)
    public Account findAccountByname(@RequestParam("name")String name) throws Exception {
        logger.info("################find account by name" + name);
        Account returnAccount = accountDaoService.getAccountbyName(name);
        return returnAccount;
    }

    @PostMapping(value = "save",produces = MediaType.APPLICATION_JSON_VALUE)
    public Account saveAccount(@RequestBody Account account) {
        logger.info("################save account" + account.getAccountName());
        Account returnAccount = accountDaoService.saveAccount(account);

        return returnAccount;
    }

    @DeleteMapping(value = "delete",produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deletAccount(@RequestBody Account account) {
        logger.info("################delete Account" + account.getAccountName());
        return accountDaoService.deleteAccount(account);
    }

}
