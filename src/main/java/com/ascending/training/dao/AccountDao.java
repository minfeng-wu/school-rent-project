package com.ascending.training.dao;

import com.ascending.training.model.Account;
//import com.ascending.training.model.School;

import java.util.List;

public interface AccountDao {
    Account save(Account account) ;
    Account update(Account account);
    boolean deleteByName(String accountName);
    boolean delete(Account account);
    List<Account> getAccounts();
    Account getAccountById(Long id);
   // List<School> getDepartmentsWithChildren();
    Account getAccountByName(String accountName);
}
