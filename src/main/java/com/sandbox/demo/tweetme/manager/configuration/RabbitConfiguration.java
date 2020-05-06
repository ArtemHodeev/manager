package com.sandbox.demo.tweetme.manager.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sandbox.demo.tweetme.manager.configuration.properties.AmqpProperties;
import com.sandbox.demo.tweetme.manager.configuration.properties.ModifyQueueProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
    @Autowired
    AmqpProperties amqpProperties;

    @Autowired
    ModifyQueueProperties modifyQueueProperties;

    @Bean
    @ConditionalOnMissingBean
    public ConnectionFactory rabbitConnectionFactory() throws Exception {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost(amqpProperties.getHost());
        cachingConnectionFactory.setPort(amqpProperties.getPort());
        cachingConnectionFactory.setUsername(amqpProperties.getUsername());
        cachingConnectionFactory.setPassword(amqpProperties.getPassword());
        cachingConnectionFactory.setVirtualHost(amqpProperties.getVirtualHost());

        return cachingConnectionFactory;
    }

    @Bean
    @ConditionalOnMissingBean
    public Queue tweetModifyQueue() {
        return new Queue(modifyQueueProperties.getName(), modifyQueueProperties.getDurable(),false, false);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}
