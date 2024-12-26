package com.demo.email_sender_service.handlers;

import com.demo.email_sender_service.constants.ChannelConstants;
import com.demo.email_sender_service.model.IncomingMessage;
import com.demo.email_sender_service.model.Notification;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class FlowEndHandler {

    @ServiceActivator(inputChannel = ChannelConstants.FLOW_END_CHANNEL)
    public void handleFlowEnd(IncomingMessage<byte[], Notification> incomingMessage) throws IOException {
        log.info("msg ack complete; flow end");
    }

}
