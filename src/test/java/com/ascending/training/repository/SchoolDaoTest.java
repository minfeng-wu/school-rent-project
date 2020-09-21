package com.ascending.training.repository;

import com.ascending.training.dao.SchoolDao;
import com.ascending.training.init.AppInitializer;
import com.ascending.training.model.Account;
import com.ascending.training.model.Item;
import com.ascending.training.model.Order;
import com.ascending.training.model.School;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class SchoolDaoTest {
    private Logger logger = LoggerFactory.getLogger(SchoolDaoTest.class);
    @Autowired
    private SchoolDao schoolDao;


    private School testSchool;
    private Account account1;
    private Order order1;
    private Item item1;



    @BeforeClass
    public static void setupOnce(){
        //schoolDao = new SchoolDaoImpl();
    }

    @Before
    public void setup(){
        testSchool = getSchoolForTest("BU", "Boston", "Massacus", "123123 street");

//     school2 = getSchoolForTest("NU", "Boston", "Massacus", "123123 street");

       account1 = new Account();
       account1.setFirstName("Xiguang");
       account1.setLastName("Zhang");
       account1.setAddress("5500 street");



//       order1 = new Order();
//       String str="2015-03-31";
//       Date date= Date.valueOf(str);
//       order1.setStart(date);

  //     account1.addOrder(order1);
  //     item1.addOrder(order1);
       testSchool.addAccount(account1);
 //     accountDao.save(account)



       testSchool = schoolDao.save(testSchool);


    }

    @After
    public void teardown(){
        schoolDao.delete(testSchool);
    }

//    @Test
//    public void saveSchoolHibernateTest() throws SQLException {
//
//        School schoolSaved = schoolDao.save(school);
//        assertNotNull("A saved School should have a ID with NULL value", schoolSaved.getId());
//        logger.info("School = {}", schoolSaved);
//    }

    @Test
    public void getSchoolByIdTest(){
        School retrievedSchool = schoolDao.getSchoolById(testSchool.getId());
        assertEquals("id should be the same", retrievedSchool.getId(), testSchool.getId());
        logger.info("School = {}", retrievedSchool);
    }

    @Test
    public void getSchoolByNameTest(){
        School retrievedSchool = schoolDao.getSchoolByName(testSchool.getName());
        assertEquals("id should be the same", retrievedSchool.getId(), testSchool.getId());
        logger.info("School = {}", retrievedSchool);
    }



    @Test
    public void getSchoolTest(){
        List<School> schoolList = schoolDao.getschools();
        assertEquals(4,schoolList.size());
    }




    private School getSchoolForTest(String name, String city, String state, String address){
        School school = new School();
        school.setName(name);
        school.setCity(city);
        school.setState(state);
        school.setAddress(address);
        return school;
    }
}
