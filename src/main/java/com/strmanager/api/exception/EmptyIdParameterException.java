package com.strmanager.api.exception;

public class EmptyIdParameterException extends RuntimeException {

    public EmptyIdParameterException() {
        super();
    }

    public EmptyIdParameterException(String message) {
        super(message);
    }

    public EmptyIdParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyIdParameterException(Throwable cause) {
        super(cause);
    }

}
