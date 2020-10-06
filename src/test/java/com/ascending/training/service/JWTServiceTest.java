package com.ascending.training.service;

import com.ascending.training.dao.UserDao;
import com.ascending.training.init.AppInitializer;
import com.ascending.training.model.User;
import com.ascending.training.repository.SchoolDaoTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class JWTServiceTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserDao userDao;

    private User user;

    @Before
    public void setUp(){
        String username = "dwang";
        user = userDao.getUserByName(username);
    }

    @Test
    public void generateTokenTest(){
        //1. get token by call jwtservice
        //2. assert not null
        //3. parse the token string using "." to get a string array
        //4. assert the string array size = 3
        String token = "";

    }

    @Test
    public void decryptJwsTokenTest(){
        //1. generate token using user
        //2. jws service decry token to get claims
        //3. get subject of the token from claims
        //4. assert user.getName = subject
    }

    @Test
    public void tokenHasNotExpiredTest(){
        /*
    1. get a toke using the user
    2. assert not null
    3. call jwtService.hasTokenExpired(token)
    3. assertFalse(flag)
         */
    }
}
