package com.demo.email_sender_service.config;

import com.demo.email_sender_service.constants.ChannelConstants;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.dsl.IntegrationFlow;

@Configuration
public class RabbitMQInboundConfig {
    private String emailMainQueueName;

    private CachingConnectionFactory rabbitMQConnectionFactory;

    public RabbitMQInboundConfig(@Value("${notifications.email.main-queue-routing-key}") String emailMainQueueName, @Qualifier("rabbitmq-conn-factory") CachingConnectionFactory rabbitMQConnectionFactory) {
        this.emailMainQueueName = emailMainQueueName;
        this.rabbitMQConnectionFactory = rabbitMQConnectionFactory;
    }

    @Bean
    public IntegrationFlow inboundConfig() {
        return IntegrationFlow.from(Amqp
                .inboundAdapter(this.rabbitMQConnectionFactory, this.emailMainQueueName)
                .configureContainer(mlcs -> {
                    mlcs.acknowledgeMode(AcknowledgeMode.MANUAL);
                    mlcs.concurrentConsumers(5);
                    mlcs.prefetchCount(25);
                })
                .errorChannel(ChannelConstants.ERROR_CHANNEL)
        ).channel(ChannelConstants.INBOUND_MSG_CHANNEL).get();
    }

}
