package com.copious.training.exception;

import java.util.Date;

public class ExceptionResponse {
    private final Date timeStamp;
    private final String message;
    private final String description;

    public ExceptionResponse(Date timeStamp, String message, String description) {

        super();
        this.timeStamp = timeStamp;
        this.message = message;
        this.description = description;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

}
