package com.ascending.training.service;


import com.ascending.training.dao.AccountDao;
import com.ascending.training.model.Account;
import com.ascending.training.model.School;
import com.ascending.training.repository.AccountDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountDaoService {
    @Autowired
    private AccountDaoImpl accountDao;

    public List<Account> getAllAccounts(){
        List<Account> accountList = accountDao.getAccounts();
        return accountList;
    }

    public  Account getAccountbyName(String name) {
        Account account = accountDao.getAccountByName(name);
        return account;
    }

    public Account saveAccount(Account account) {
        accountDao.save(account);
        return account;
    }

    public boolean deleteAccount(Account account) {
        return accountDao.delete(account);
    }
}
