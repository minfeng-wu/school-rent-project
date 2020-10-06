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
    private School testSchool;


    @Autowired
    private SchoolDaoService schoolDaoService;

    @Before
    public void setup() {
        testSchool = getSchoolForTest("BU", "Boston", "Massacus", "123123 street");
        schoolDaoService.saveSchool(testSchool);
    }

    @After
    public void teardown(){
        schoolDaoService.deleteSchool(testSchool);
    }


    private School getSchoolForTest(String name, String city, String state, String address){
        School school = new School();
        school.setName(name);
        school.setCity(city);
        school.setState(state);
        school.setAddress(address);
        return school;
    }






    @Test
    public void getSchoolByNameTest(){
        School retrievedSchool = schoolDaoService.getSchoolbyName(testSchool.getName());
        assertEquals("id should be the same", retrievedSchool.getId(), testSchool.getId());
        logger.info("School = {}", retrievedSchool);
    }


    @Test
    public void getSchoolTest(){
        List<School> schoolList = schoolDaoService.getAllSchools();
        assertEquals(4,schoolList.size());
    }


}
