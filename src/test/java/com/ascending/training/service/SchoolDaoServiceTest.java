package com.ascending.training.service;

import com.ascending.training.dao.SchoolDao;
import com.ascending.training.init.AppInitializer;
import com.ascending.training.model.Account;
import com.ascending.training.model.School;
import com.ascending.training.repository.SchoolDaoTest;
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
public class SchoolDaoServiceTest {
    private Logger logger = LoggerFactory.getLogger(SchoolDaoTest.class);
    //private School testSchool;

    @Autowired
    private SchoolDaoService schoolDaoService;









    @Test
    public void getSchoolTest(){
        List<School> schoolList = schoolDaoService.getAllSchools();
        assertEquals(3,schoolList.size());
    }


}
