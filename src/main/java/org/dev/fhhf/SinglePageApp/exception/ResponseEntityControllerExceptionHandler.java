package org.dev.fhhf.SinglePageApp.exception;

import org.dev.fhhf.SinglePageApp.model.AppError;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseEntityControllerExceptionHandler
            extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            DataNotFoundException.class,
            DataIntegrityViolationException.class,
            EmptyResultDataAccessException.class
    }) protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request){

        String errorMsg = ex.getMessage();
        AppError responseBody = new AppError(errorMsg,404, "https://github.com/BalthazRBlake");
        return handleExceptionInternal(ex, responseBody,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
