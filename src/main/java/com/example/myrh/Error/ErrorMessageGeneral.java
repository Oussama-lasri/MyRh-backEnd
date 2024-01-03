package com.example.myrh.Error;

public enum ErrorMessageGeneral {
    RECORD_ALREADY_EXISTS("Record already exist "),
    INTERNAL_SERVER_ERROR("server error"),
    NO_RECORD_FOUND("record with provided id is not found"),;

    private final String errorMessage;

    ErrorMessageGeneral(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}