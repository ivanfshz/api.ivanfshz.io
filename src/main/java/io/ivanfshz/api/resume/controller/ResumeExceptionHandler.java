package io.ivanfshz.api.resume.controller;

import io.ivanfshz.api.resume.exception.ResumeException;
import io.ivanfshz.api.resume.model.Error;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@RestControllerAdvice
public class ResumeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex,
                                                      WebRequest request) {
        Error error = Error.builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .detail(request.getDescription(false))
                .build();
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResumeException.class)
    public ResponseEntity<Object> handleResumeException(ResumeException ex,
                                                        WebRequest request) {
        final Error error = Error.builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .detail(request.getDescription(Boolean.FALSE))
                .build();
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        final Error error = Error.builder()
                .timestamp(new Date())
                .message("Fields validation are wrong.")
                .detail(ex.getBindingResult().toString())
                .build();
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}