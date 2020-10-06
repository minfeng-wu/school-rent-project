package com.ascending.training.controller;

import com.ascending.training.model.User;
import com.ascending.training.service.UserDaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserDaoService userDaoService;

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public User findUserBynameAndEmail(@RequestParam("name")String name,
                                       @RequestParam("email")String email) throws Exception {
        return userDaoService.findUserBynameAndEmail(name, email);
    }
}
