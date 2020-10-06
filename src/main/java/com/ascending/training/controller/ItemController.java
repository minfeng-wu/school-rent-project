package com.ascending.training.controller;

import com.ascending.training.service.AccountDaoService;
import com.ascending.training.service.ItemDaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/item")
public class ItemController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ItemDaoService itemDaoService;
}
