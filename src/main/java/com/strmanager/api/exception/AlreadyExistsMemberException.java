package com.strmanager.api.exception;

public class AlreadyExistsMemberException extends RuntimeException {

    public AlreadyExistsMemberException() {
        super();
    }

    public AlreadyExistsMemberException(String message) {
        super(message);
    }

    public AlreadyExistsMemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistsMemberException(Throwable cause) {
        super(cause);
    }

}
