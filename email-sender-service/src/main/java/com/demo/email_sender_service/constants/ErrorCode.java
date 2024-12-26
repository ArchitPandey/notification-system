package com.demo.email_sender_service.constants;

public enum ErrorCode {

    PROCESSING_ERROR("Message Processing Error"),

    INVALID_MSG("Invalid message error");

    private String code;

    public String getCode() {
        return this.code;
    }

    ErrorCode(String code) {
        this.code = code;
    }
}
