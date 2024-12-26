package com.demo.notification_service.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpProducerConfig {

    @Value("${rabbitmq.outbound.broker-url}")
    private String brokerUrl;

    @Value("${rabbitmq.outbound.user}")
    private String user;

    @Value("${rabbitmq.outbound.password}")
    private String password;

    @Bean("notification-producer-template")
    public RabbitTemplate producerTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }

    @Bean
    public CachingConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(brokerUrl);
        connectionFactory.setUsername(user);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

}
