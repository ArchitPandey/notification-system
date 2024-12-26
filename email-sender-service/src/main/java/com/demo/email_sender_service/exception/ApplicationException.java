package com.demo.email_sender_service.exception;

import com.demo.email_sender_service.constants.ErrorCode;
import lombok.ToString;

@ToString
public class ApplicationException extends Exception {

    private ErrorCode errorCode;

    private String message;

    public ApplicationException(ErrorCode errorCode, String msg) {
        this.errorCode = errorCode;
        this.message = msg;
    }

}
