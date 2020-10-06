package com.ascending.training.dao;

import com.ascending.training.model.Role;
import com.ascending.training.model.User;

import java.util.List;

public interface UserDao {
    User save(User user);
    User getUserByEmail(String email);
    User getUserById(Long Id);
    User getUserByCredentials(String email, String password) throws Exception;
    User findUserBynameAndEmail(String name, String email) throws Exception;
    User addRole(User user, Role role);
    int delete(User u);
    List<User> findAllUsers();
    User getUserByName(String username);
}
