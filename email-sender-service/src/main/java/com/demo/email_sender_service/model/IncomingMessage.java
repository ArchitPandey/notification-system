package com.demo.email_sender_service.model;

import lombok.*;
import org.springframework.messaging.MessageHeaders;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class IncomingMessage<T,U> {

    private T payload;

    private MessageHeaders headers;

    private U pojoBody;

}
