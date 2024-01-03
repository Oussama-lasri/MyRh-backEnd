package com.example.myrh.Error;

public enum ErrorMessageOffre {
    EMAIL_ALREADY_EXIST("Email already exist"),
    INTERNAL_SERVER_ERROR("server error"),
    NO_RECORD_FOUND("this Offer with provided id is not found"),;

    private final String errorMessage;

    ErrorMessageOffre(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}
