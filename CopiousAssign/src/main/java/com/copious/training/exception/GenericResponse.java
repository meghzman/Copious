package com.copious.training.exception;

import java.io.Serializable;

public class GenericResponse<T> implements Serializable {
    private static final Long serialVersionId = 123457L;

    private final Boolean isSuccessful;
    private final String message;
    private transient T payload;

    public GenericResponse(Boolean isSuccessful, String message) {
        this.isSuccessful = isSuccessful;
        this.message = message;
    }

    public GenericResponse(Boolean isSuccessful, String message, T payload) {
        this.isSuccessful = isSuccessful;
        this.message = message;
        this.payload = payload;
    }

    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public String getMessage() {
        return message;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
