package com.demo.email_sender_service.model;

public enum NotificationType {

    EMAIL("email");

    private String type;

    public String getType() {
        return this.type;
    }

    NotificationType(String type) {
        this.type = type;
    }

}
