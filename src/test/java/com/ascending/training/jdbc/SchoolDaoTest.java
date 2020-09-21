package com.ascending.training.jdbc;

import com.ascending.training.dao.SchoolDao;
import com.ascending.training.model.School;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class SchoolDaoTest {
    private SchoolDao schoolDao;
    private  Logger logger = LoggerFactory.getLogger(SchoolDaoImplement.class);
    @Before
    public void setup(){
        schoolDao = new SchoolDaoImplement();
    }

    @After
    public void teardown(){

    }

    @Test
    public void getSchholsTest(){

        //SchoolJDBCDao schoolJDBCDao = new SchoolJDBCDaoImpl();
        List<School> schoolList = schoolDao.getschools();
        for(School school : schoolList){
            logger.info("Department = {}", school );
        }
    }

    @Test
    public void saveSchoolTest() throws SQLException {
        School school = new School();
        school.setAddress("123123123");
        school.setCity("Saratoga Spring");
        school.setName("Skidmore College");
        school.setState("NY");

       // SchoolJDBCDao schoolJDBCDao = new SchoolJDBCDaoImpl();
        schoolDao.save(school);
    }
}
