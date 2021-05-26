package com.copious.training.exception.category;

import com.copious.training.model.ErrorMessages;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException {

    private final String message;
    private final HttpStatus status;

    public UserNotFoundException(ErrorMessages messages) {
        this.message = messages.getErrorMessage();
        this.status = messages.getHttpStatus();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
