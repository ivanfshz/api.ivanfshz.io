package io.ivanfshz.api.resume.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResumeException extends RuntimeException {

    public ResumeException(final String message) {
        super(message);
    }
}