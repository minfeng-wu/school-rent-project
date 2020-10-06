package com.ascending.training.repository;

import com.ascending.training.dao.AccountDao;
import com.ascending.training.dao.ItemDao;
import com.ascending.training.dao.SchoolDao;
import com.ascending.training.init.AppInitializer;
import com.ascending.training.model.Account;
import com.ascending.training.model.Item;
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

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class ItemDaoTest {
    private Logger logger = LoggerFactory.getLogger(ItemDaoTest.class);
    private Item testItem;

    @Autowired
    private ItemDao itemDao;


    @Before
    public void setup(){
        testItem = new Item();
        testItem.setAvaliability(true);
        testItem.setBrand("Apple");
        testItem.setName("Mac pro 2020");
        testItem.setCategory("computer");
        testItem.setPrice(new BigDecimal("100"));

        itemDao.save(testItem);
    }

    @After
    public void tearDown(){
        itemDao.delete(testItem);
    }

    @Test
    public void getAllItems(){
        List<Item> itemList = itemDao.getItems();
        assertEquals(3,itemList.size());
    }
}
