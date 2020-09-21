package com.ascending.training.service;


import com.ascending.training.dao.AccountDao;
import com.ascending.training.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountDaoService {
    @Autowired
    private AccountDao accountDao;

    public List<Account> getAllSchools(){
        List<Account> accountList = accountDao.getAccounts();
        return accountList;
    }
}
