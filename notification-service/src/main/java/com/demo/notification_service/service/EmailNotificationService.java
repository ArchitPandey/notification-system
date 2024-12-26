package com.demo.notification_service.service;

import com.demo.notification_service.model.Notification;
import com.demo.notification_service.model.NotificationType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class EmailNotificationService {

    private RabbitTemplate publisherTemplate;

    private String exchange;

    private String routingKey;

    private ObjectMapper objectMapper;

    public EmailNotificationService(ObjectMapper objectMapper,
                                    @Qualifier("notification-producer-template") RabbitTemplate publisherTemplate,
                                    @Value("${notifications.email.main-exchange}") String exchange,
                                    @Value("${notifications.email.main-queue-routing-key}") String routingKey
    ) {
        this.objectMapper = objectMapper;
        this.publisherTemplate = publisherTemplate;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    public void publishNotification(String to, String body) throws JsonProcessingException {
        try {
            Notification notification = new Notification(NotificationType.EMAIL, to, body);
            String json = this.objectMapper.writeValueAsString(notification);

            log.info("sending json {}; exchange {}; routingKey {}", json, exchange, routingKey);
            this.publisherTemplate.send(exchange, routingKey, createMessage(json));
        } catch (Exception e) {
            log.error("error sending notification", e);
            throw e;
        }
    }

    private Message createMessage(String body) {
        MessageProperties properties = new MessageProperties();
        properties.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
        return new Message(body.getBytes(StandardCharsets.UTF_8), properties);
    }

}
