package com.strmanager.api.exception;

public class ShortPasswordException extends RuntimeException {

    public ShortPasswordException() {
        super();
    }

    public ShortPasswordException(String message) {
        super(message);
    }

    public ShortPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShortPasswordException(Throwable cause) {
        super(cause);
    }
}
