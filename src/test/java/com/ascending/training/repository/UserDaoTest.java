package com.ascending.training.repository;

import com.ascending.training.dao.RoleDao;
import com.ascending.training.dao.UserDao;
import com.ascending.training.init.AppInitializer;
import com.ascending.training.model.Role;
import com.ascending.training.model.School;
import com.ascending.training.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class UserDaoTest {
    private Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;
    private User user;
    private Role role1;
    private Role role2;

    @Before
    public void init() {

        user = new User();
        user.setEmail("user12@gmail.com");
        user.setFirstName("LIYING");
        user.setLastName("Huang");
        user.setName("username");
        user.setPassword("1111");
        user.setSecretKey("1222");
//        user1 = userDao.save(user1);
//        userDao.save(user1);
        role1 = new Role();
        role1.setName("role1_manager");
        role1.setAllowedCreate(true);
        role1.setAllowedDelete(true);
        role1.setAllowedRead(true);
        role1.setAllowedResource("yes");
        role1.setAllowedUpdate(true);
        role1 = roleDao.save(role1);
        user.addRole(role1);
        //role1.setUsers(user1);
        //user1 = userService.setRole(user1.getName(), role1.getName());
        role2 = new Role();
        role2.setName("role2_employee");
        role2.setAllowedCreate(false);
        role2.setAllowedDelete(false);
        role2.setAllowedRead(true);
        role2.setAllowedResource("no");
        role2.setAllowedUpdate(false);
        role2 = roleDao.save(role2);
        user.addRole(role2);
//        user1 = userDao.save(user1);
        userDao.save(user);
    }

    @After
    public void tearDown() {
        userDao.delete(user);
        roleDao.delete(role1);
        roleDao.delete(role2);
    }



    @Test
    public void getUserByCredentials(){
        String email = "dwang@training.ascendingdc.com";
        String password = "25f9e794323b453885f5181f1b624d0b";
        User user = null;
        try {
            user = userDao.getUserByCredentials(email,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(email, user.getEmail());
    }

    @Test
    public void getUserByUserAndEmail(){
        String email = "dwang@training.ascendingdc.com";
        String name = "dwang";
        User user = null;
        try {
            user = userDao.findUserBynameAndEmail(name, email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(email, user.getEmail());
    }
}
