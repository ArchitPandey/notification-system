package com.demo.email_sender_service.handlers;

import com.demo.email_sender_service.constants.ChannelConstants;
import com.demo.email_sender_service.constants.ErrorCode;
import com.demo.email_sender_service.constants.MQAckNackModes;
import com.demo.email_sender_service.exception.ApplicationException;
import com.demo.email_sender_service.model.IncomingMessage;
import com.demo.email_sender_service.model.Notification;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
public class MessageProcessingHandler {

    @ServiceActivator(inputChannel = ChannelConstants.MSG_PROCESSING_CHANNEL, outputChannel = ChannelConstants.FLOW_END_CHANNEL)
    public IncomingMessage<byte[], Notification> handler(IncomingMessage<byte[], Notification> incomingMessage) throws ApplicationException, IOException {
        log.info("msg processing: start");
        boolean isValid = isValidMessage(incomingMessage);

        if (!isValid) {
            log.error("invalid message {}", incomingMessage.getPojoBody().toString());
            manualAckNack(MQAckNackModes.NACK, incomingMessage);
            throw new ApplicationException(ErrorCode.INVALID_MSG, "invalid msg");
        } else {
            try {
                mockProcessing();
                log.info("msg processing complete");
                manualAckNack(MQAckNackModes.ACK, incomingMessage);
                return incomingMessage;
            } catch (Exception e) {
                log.error("error processing message", e);
                manualAckNack(MQAckNackModes.NACK, incomingMessage);
                throw e;
            }
        }
    }

    private boolean isValidMessage(IncomingMessage<byte[], Notification> incomingMessage) {
        String to = incomingMessage.getPojoBody().getTo();

        if (Objects.isNull(to) || to.isBlank() ) {
            return false;
        }

        String body = incomingMessage.getPojoBody().getNotificationBody();

        if (Objects.isNull(body) || body.isBlank() ) {
            return false;
        }

        return true;
    }

    private void mockProcessing() throws ApplicationException {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            log.error("error when mock processing ", e);
            throw new ApplicationException(ErrorCode.PROCESSING_ERROR, e.getMessage());
        }
    }

    private void manualAckNack(MQAckNackModes mode, IncomingMessage<byte[], Notification> incomingMessage) throws IOException {
        Long tag = incomingMessage.getHeaders().get(AmqpHeaders.DELIVERY_TAG, Long.class);
        Channel channel = incomingMessage.getHeaders().get(AmqpHeaders.CHANNEL, Channel.class);
        if (mode.equals(MQAckNackModes.ACK)) {
            channel.basicAck(tag, false);
        } else {
            channel.basicNack(tag, false, false);
        }
    }

}
