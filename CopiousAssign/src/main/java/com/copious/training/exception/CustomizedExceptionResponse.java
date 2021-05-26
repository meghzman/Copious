package com.copious.training.exception;

import com.copious.training.exception.category.MissingRequiredFieldException;
import com.copious.training.exception.category.UserNotFoundException;
import com.copious.training.exception.category.UserSignUpServiceException;
import com.copious.training.model.ErrorMessages;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomizedExceptionResponse extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed",
                ex.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    //User not found Exception
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));


        GenericResponse<ExceptionResponse> genericResponse = new GenericResponse<>(false, ErrorMessages.NO_RECORDS_FOUND
                .getHttpStatus().name(), exceptionResponse);

        HttpHeaders headers = new HttpHeaders();
        headers.set("header name", "header value");

        return handleExceptionInternal(ex, genericResponse, headers, ErrorMessages.NO_RECORDS_FOUND.getHttpStatus(), request);
    }

    //Missing one or more required fields
    @ExceptionHandler(MissingRequiredFieldException.class)
    public final ResponseEntity<Object> handleRequiredFieldsMissingException(MissingRequiredFieldException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        GenericResponse<ExceptionResponse> genericResponse = new GenericResponse<>(false, ErrorMessages.MISSING_REQUIRED_FIELD
                .getHttpStatus().name(), exceptionResponse);

        return handleExceptionInternal(ex, genericResponse, new HttpHeaders(), ErrorMessages.MISSING_REQUIRED_FIELD.getHttpStatus(), request);


    }

    @ExceptionHandler(UserSignUpServiceException.class)
    public final ResponseEntity<Object> handleUserSignUpServiceExceptions(UserSignUpServiceException ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));

        GenericResponse<ExceptionResponse> genericResponse = new GenericResponse<>(false, ErrorMessages.RECORD_ALREADY_EXIST
                .getHttpStatus().name(), exceptionResponse);

        return handleExceptionInternal(ex, genericResponse, new HttpHeaders(), ErrorMessages.RECORD_ALREADY_EXIST.getHttpStatus(), request);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public final ResponseEntity<Object> customValidationErrorHandling(MethodArgumentNotValidException exception, WebRequest request) {
//
//        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation error",
//                exception.getBindingResult().getFieldError().getDefaultMessage());
//
//        GenericResponse<ExceptionResponse> genericResponse = new GenericResponse<>(false, ErrorMessages.Validation_Failed
//                .getHttpStatus().name(), exceptionResponse);
//
//        return handleExceptionInternal(exception, genericResponse, new HttpHeaders(), ErrorMessages.Validation_Failed.getHttpStatus(), request);
//    }
}
