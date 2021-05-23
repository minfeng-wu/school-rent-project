package com.ascending.training.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AWSMessageService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    public void setLogger(Logger logger){this.logger = logger;}

    @Autowired
    AmazonSQS amazonSQS;

    public String createQueue(String queueName){
        String queueUrl = null;

        try{
            queueUrl = getQueueUrl(queueName);
        }catch(QueueDoesNotExistException e){
            CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueName);
            queueUrl = amazonSQS.createQueue(createQueueRequest).getQueueUrl();
        }
        return queueUrl;
    }

    public String getQueueUrl(String queueName){
        GetQueueUrlResult getQueueUrlResult = amazonSQS.getQueueUrl(queueName);
        logger.info("*******QueueUrl: "+ getQueueUrlResult.getQueueUrl());
        return getQueueUrlResult.getQueueUrl();
    }

    public List<Message> getMessages(String queueName) {
        String queueUrl = getQueueUrl(queueName);
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl);
        List<Message> messages = amazonSQS.receiveMessage(receiveMessageRequest).getMessages();
        return messages;
    }

    public void sendMessage(String queueName, String msg) {
        Map<String, MessageAttributeValue> messageAttributes = new HashMap();
        MessageAttributeValue messageAttributeValue = new MessageAttributeValue();
        messageAttributeValue.withDataType("String").withStringValue("File URL in S3");
        messageAttributes.put("message", messageAttributeValue);
        String queueUrl = getQueueUrl(queueName);
        SendMessageRequest sendMessageRequest = new SendMessageRequest();
        sendMessageRequest.withQueueUrl(queueUrl)
                .withMessageBody(msg)
                .withMessageAttributes(messageAttributes);
        amazonSQS.sendMessage(sendMessageRequest);
    }


    // same as requestMapping or getMapping
    // This causes a listener container to be created on the specified destination using a ContainerFactory.
    // If not set, a default container factory is assumed to be available with a bean name of jmsListenerContainerFactory unless an explicit default has been provided through configuration.
    // Need to create container Listener(Already create bean in JmsConfig)
   // @JmsListener(destination = "test")
    @JmsListener(destination = "${aws.qsq.name}")
    public void receiveMessage(String message){
        logger.info("========jms listener recived message = {}", message);
    }

}
