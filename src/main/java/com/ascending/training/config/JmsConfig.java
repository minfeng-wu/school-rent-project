package com.ascending.training.config;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazonaws.services.sqs.AmazonSQS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.jms.support.destination.DynamicDestinationResolver;
import org.springframework.util.ErrorHandler;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;


import javax.jms.Session;

//It is a generic config
@Configuration
//Spring Boot cover this annotation
@EnableJms
public class JmsConfig {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AmazonSQS sqs;

    @Bean(name = "awsQueueConnectionFactory")
    public SQSConnectionFactory getSQSConnectionFactory(){
        return new SQSConnectionFactory(new ProviderConfiguration(), sqs);
    }

    // Serialize message content to json using TextMessage
    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
    @Bean
    public ErrorHandler defaultErrorHandler() {
        return new ErrorHandler() {
            @Override
            public void handleError(Throwable throwable) {
                // print error...
                // send email and SMS...
                System.err.println(throwable.getMessage());
            }
        };
    }

    @Bean(name = "jmsListenerContainerFactory")
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(){

        //Create JmsListener
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

        //Use AWS queue connection factory to connect to AWS queues
        factory.setConnectionFactory(getSQSConnectionFactory());

        //SQS does not support transacted
        factory.setDestinationResolver(new DynamicDestinationResolver());
        factory.setConcurrency("3-10");
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);

        // Set error handler (optional)
        // factory.setErrorHandler(defaultErrorHandler());
        // Set message converter (optional)
        // Will have error if converter cannot convert the message.
        // factory.setMessageConverter(jacksonJmsMessageConverter());

        return factory;
    }
}
