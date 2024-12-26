package com.demo.email_sender_service.config;

import com.demo.email_sender_service.constants.ChannelConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.integration.config.EnableIntegration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class ChannelConfig {

    @Bean(name = ChannelConstants.INBOUND_MSG_CHANNEL)
    public DirectChannel rabbitMQIboundChannel() {
        return new DirectChannel();
    }

    @Bean(name = ChannelConstants.MSG_PROCESSING_CHANNEL)
    public DirectChannel msgTransformerChannel() {
        return new DirectChannel();
    }

    @Bean(name = ChannelConstants.FLOW_END_CHANNEL)
    public DirectChannel flowEndChannel() {
        return new DirectChannel();
    }

    @Bean(name = ChannelConstants.ERROR_CHANNEL)
    public DirectChannel errorChannel() {
        return new DirectChannel();
    }

}
