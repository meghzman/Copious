package com.copious.training.model;

import org.springframework.http.HttpStatus;

public enum ErrorMessages {
    MISSING_REQUIRED_FIELD("One or more fields are missing", HttpStatus.BAD_REQUEST),
    RECORD_ALREADY_EXIST("User already Exist", HttpStatus.BAD_REQUEST),
    Name_ALREADY_EXIST("Name already Exist", HttpStatus.BAD_REQUEST),
    Mobile_ALREADY_EXIST("Mobile already Exist", HttpStatus.BAD_REQUEST),
    Id_ALREADY_EXIST("Id already Exist", HttpStatus.BAD_REQUEST),
    Validation_Failed("Validation Exist", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_RECORDS_FOUND("No records found", HttpStatus.NOT_FOUND),
    AUTHENTICATION_FAILED("Authentication failed", HttpStatus.UNAUTHORIZED),
    COULD_NOT_DELETE_RECORD("Could not delete all records as list is already empty", HttpStatus.BAD_REQUEST);

    private final String errorMessage;
    private final HttpStatus httpStatus;

    ErrorMessages(String errorMessage, HttpStatus httpStatus) {
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
