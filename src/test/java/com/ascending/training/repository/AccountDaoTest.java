package com.ascending.training.repository;

import com.ascending.training.dao.AccountDao;
import com.ascending.training.dao.SchoolDao;
import com.ascending.training.init.AppInitializer;
import com.ascending.training.model.Account;
import com.ascending.training.model.School;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class AccountDaoTest {
    private Logger logger = LoggerFactory.getLogger(AccountDaoTest.class);
    private Account testAccount;
    private School testSchool;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private SchoolDao schoolDao;


    @Before
    public void setup(){


        testAccount = new Account();
        testAccount.setFirstName("Xiguang");
        testAccount.setLastName("Zhang");
        testAccount.setAddress("5500 street");
        testSchool = schoolDao.getSchoolByName("UMD");
        testAccount.setSchool(testSchool);
        accountDao.save(testAccount);





    }

    @After
    public void teardown(){
        accountDao.delete(testAccount);
    }

    @Test
    public void getAccountsTest(){
        List<Account> accountList = accountDao.getAccounts();
        assertEquals(3,accountList.size());
    }

    @Test
    public void getAccountByIdTest(){
        Account retrievedAccount= accountDao.getAccountById(testAccount.getId());
        assertEquals("id should be the same", retrievedAccount.getId(), testAccount.getId());
        logger.info("School = {}", retrievedAccount);
    }

    @Test
    public void getAccountByNameTest(){
        Account retrievedAccount= accountDao.getAccountByName(testAccount.getAccountName());
        assertEquals("id should be the same", retrievedAccount.getId(), testAccount.getId());
        logger.info("School = {}", retrievedAccount);
    }

}
