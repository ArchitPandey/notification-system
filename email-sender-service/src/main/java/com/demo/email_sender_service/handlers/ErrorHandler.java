package com.demo.email_sender_service.handlers;

import com.demo.email_sender_service.constants.ChannelConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ErrorHandler {

    @ServiceActivator(inputChannel = ChannelConstants.ERROR_CHANNEL)
    public void handleError(Throwable e) {
        log.error("error while processing msg ", e);
    }

}
