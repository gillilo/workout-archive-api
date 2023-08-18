package com.strmanager.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotExistsEmailException extends RuntimeException {

    public NotExistsEmailException() {
        super();
    }

    public NotExistsEmailException(String message) {
        super(message);
    }

    public NotExistsEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotExistsEmailException(Throwable cause) {
        super(cause);
    }

}
