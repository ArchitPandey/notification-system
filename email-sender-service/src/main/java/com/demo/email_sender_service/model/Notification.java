package com.demo.email_sender_service.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Notification {

    private NotificationType type;

    private String to;

    private String notificationBody;

}
