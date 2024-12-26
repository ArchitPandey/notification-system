package com.demo.notification_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Notification {

    private NotificationType type;

    private String to;

    private String notificationBody;

}
