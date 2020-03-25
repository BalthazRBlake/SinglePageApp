package org.dev.fhhf.SinglePageApp.exception;

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

    @ExceptionHandler(value = {DataIntegrityViolationException.class, EmptyResultDataAccessException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request){

        String errorMsg =
                ex.getClass().getSimpleName().equals("EmptyResultDataAccessException") ?
                "Employee with given id not found" :
                "Department with given id not found";

        Error responseBody = new Error(errorMsg,404, "https://github.com/BalthazRBlake");
        return handleExceptionInternal(ex, responseBody,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
