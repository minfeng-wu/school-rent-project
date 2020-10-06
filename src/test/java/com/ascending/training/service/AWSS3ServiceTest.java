package com.ascending.training.service;

import com.ascending.training.init.AppInitializer;
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
public class AWSS3ServiceTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AWSS3Service s3Servie;

    @Test
    public void isBucketExisTest(){
        String bucketname = "bill-ascending";
        boolean isExist = s3Servie.isBucketExist(bucketname);
        assertEquals(isExist, true);
    }

}
