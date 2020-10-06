package com.ascending.training.service;

import com.ascending.training.dao.UserDao;
import com.ascending.training.model.User;
import com.ascending.training.repository.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDaoService {

    @Autowired
    private UserDaoImpl userDao;

    public User findUserBynameAndEmail(String name, String email) throws Exception {
        return userDao.getUserByCredentials(name,email);
    }

    public User findUserByCredential(String email, String password) throws Exception {
        return userDao.getUserByCredentials(email, password);
    }

    }
