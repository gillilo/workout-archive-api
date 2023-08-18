package com.strmanager.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WrongPasswordException extends RuntimeException {

    public WrongPasswordException() {
        super();
    }

    public WrongPasswordException(String message) {
        super(message);
    }

    public WrongPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongPasswordException(Throwable cause) {
        super(cause);
    }

}
