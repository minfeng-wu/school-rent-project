package com.ascending.training.service;

import com.ascending.training.init.AppInitializer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class AWSMessageServiceTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AWSMessageService awsMessageService;

    private String queueName;
    private String queueName2;

    @Before
    public void setup(){

    }

    @Test
    public void getQueueUrlTest(){
        String queuename = "test";
        String queueUrl = awsMessageService.getQueueUrl(queuename);
        assertNotNull(queueUrl);
        assertTrue(queueUrl.contains(queuename));
        logger.info("*************, ={}", queueUrl);
    }
}
