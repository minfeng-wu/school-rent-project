package com.ascending.training.service;


import com.amazonaws.services.iotanalytics.model.Message;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.GetQueueUrlRequest;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.QueueDoesNotExistException;
import com.ascending.training.init.AppInitializer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.verification.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class AWSMessageServiceMockTest {
    //private Logger logger = LoggerFactory.getLogger(getClass());

    @Spy
    private Logger logger;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private AmazonSQS amazonSQS;

    @InjectMocks
    private AWSMessageService messageService;

    private String queueName = "training_queue_ascending_com";
    private String fakeQueueURl = "www.fakeQueueUrl.com/abc/123/fake";
    private String msg = "";
    private List<Message> message;

    @Before
    public void setup(){
        message = new ArrayList<>();
        message.add(new Message());
    }

    @Test
    public void getQueueUrl(){
        GetQueueUrlResult getQueueUrlResult = mock(GetQueueUrlResult.class);
        when(amazonSQS.getQueueUrl(anyString())).thenReturn(getQueueUrlResult);
        when(getQueueUrlResult.getQueueUrl()).thenReturn(fakeQueueURl);
        String queueUrl = messageService.getQueueUrl(queueName);
        assertEquals(queueUrl, fakeQueueURl);

        verify(amazonSQS, times(1)).getQueueUrl(anyString());
    }

    @Test
    public void createQueue_happy_path(){ when(amazonSQS.createQueue(any(CreateQueueRequest.class)).getQueueUrl()).thenReturn(fakeQueueURl);
        when(amazonSQS.getQueueUrl(anyString()).getQueueUrl()).thenThrow(new QueueDoesNotExistException("Does not exist"));
        String queueUrl = messageService.createQueue(queueName);
        assertEquals(fakeQueueURl, queueUrl);
        verify(amazonSQS, timeout(10).times(1)).createQueue(any(CreateQueueRequest.class));
    }

    @Test
    public void createQueue_with_queue_exist_already(){
        GetQueueUrlResult getQueueUrlResult = mock(GetQueueUrlResult.class);
        when(amazonSQS.getQueueUrl(anyString())).thenReturn(getQueueUrlResult);
        when(getQueueUrlResult.getQueueUrl()).thenReturn(fakeQueueURl);

        String queueUrl = messageService.createQueue(queueName);
        assertEquals(fakeQueueURl, queueUrl);
        verify(amazonSQS, never()).createQueue(any(CreateQueueRequest.class));
    }
}
