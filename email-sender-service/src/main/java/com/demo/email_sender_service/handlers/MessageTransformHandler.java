package com.demo.email_sender_service.handlers;

import com.demo.email_sender_service.constants.ChannelConstants;
import com.demo.email_sender_service.model.IncomingMessage;
import com.demo.email_sender_service.model.Notification;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class MessageTransformHandler {

    private ObjectMapper objectMapper;

    public MessageTransformHandler() {
        this.objectMapper = new ObjectMapper();
    }

    @Transformer(inputChannel = ChannelConstants.INBOUND_MSG_CHANNEL, outputChannel = ChannelConstants.MSG_PROCESSING_CHANNEL)
    public IncomingMessage<byte[], Notification> transform(Message<byte[]> msg) throws IOException {
        Notification notification = this.objectMapper.readValue(msg.getPayload(), Notification.class);
        IncomingMessage<byte[], Notification> incomingMessage = new IncomingMessage();
        incomingMessage.setHeaders(msg.getHeaders());
        incomingMessage.setPayload(msg.getPayload());
        incomingMessage.setPojoBody(notification);

        log.info("incoming message: {}", incomingMessage);

        return incomingMessage;
    }

}
