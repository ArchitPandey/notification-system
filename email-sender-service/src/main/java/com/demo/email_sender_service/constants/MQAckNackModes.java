package com.demo.email_sender_service.constants;

public enum MQAckNackModes {

    ACK("ACK"),
    NACK("NACK");

    private String mode;

    public String getMode() {
        return this.mode;
    }

    MQAckNackModes(String mode) {
        this.mode = mode;
    }

}
