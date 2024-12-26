package com.demo.notification_service.api;

import com.demo.notification_service.model.EmailRequest;
import com.demo.notification_service.service.EmailNotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/send")
public class NotificationApi {

    private EmailNotificationService emailNotificationService;

    public NotificationApi(EmailNotificationService emailNotificationService) {
        this.emailNotificationService = emailNotificationService;
    }

    @PostMapping("/email")
    public ResponseEntity<String> emailNotification(@RequestBody EmailRequest emailRequest) throws JsonProcessingException {
        this.emailNotificationService.publishNotification(emailRequest.getTo(), emailRequest.getBody());
        return ResponseEntity.ok("Success");
    }

}
