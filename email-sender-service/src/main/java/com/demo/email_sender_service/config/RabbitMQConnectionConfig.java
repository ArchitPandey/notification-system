package com.demo.email_sender_service.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConnectionConfig {

    @Value("${rabbitmq.inbound.broker-url}")
    private String brokerUrl;

    @Value("${rabbitmq.inbound.user}")
    private String user;

    @Value("${rabbitmq.inbound.password}")
    private String password;

    @Bean("rabbitmq-conn-factory")
    CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(brokerUrl);
        connectionFactory.setUsername(user);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

}
