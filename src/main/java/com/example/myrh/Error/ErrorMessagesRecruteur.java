package com.example.myrh.Error;

public enum ErrorMessagesRecruteur {
    EMAIL_ALREADY_EXIST("Email already exist"),
    INTERNAL_SERVER_ERROR("server error"),
    NO_RECORD_FOUND("with provided id is not found"),;

    private final String errorMessage;

    ErrorMessagesRecruteur(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}
