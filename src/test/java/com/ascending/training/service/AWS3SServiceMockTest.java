package com.ascending.training.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.ascending.training.init.AppInitializer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class AWS3SServiceMockTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Mock
    private AmazonS3 amazonS3Mock;

    @Before
    public void setup(){
        //MockitoAnnotation.initMocks(this)
    }

    @Test
    public void testBucketDoExist(){
        when(amazonS3Mock.doesBucketExistV2(anyString())).thenReturn(Boolean.TRUE);
    }

    @Test
    public void testCreateBucket() {
        when(amazonS3Mock.createBucket(anyString())).thenReturn(new Bucket());
    }
    public void testCreateBucketWithArgumentCapture(){}
    public void testDeleteBucket(){}
    public void testUploadMultipartFile(){}

}
