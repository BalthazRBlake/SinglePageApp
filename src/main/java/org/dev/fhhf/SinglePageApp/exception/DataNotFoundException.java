package org.dev.fhhf.SinglePageApp.exception;

public class DataNotFoundException extends RuntimeException{

    private static final long serialVersionUID = -8291776472423190685L;

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

