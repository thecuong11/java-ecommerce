package com.example.javaEcommerce.enums;

import lombok.Getter;

@Getter
public enum MessageEnum {
    SUCCESS("20001", "Success"),
    ERR_APPLICATION("30000", "Application error"),
    ERR_INVALID_TOKEN("3001", "Token is invalid");

    private String code;
    private String message;

    MessageEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
