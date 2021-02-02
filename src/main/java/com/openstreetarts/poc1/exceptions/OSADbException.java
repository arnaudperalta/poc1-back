package com.openstreetarts.poc1.exceptions;

public class OSADbException extends OSAException {

    public OSADbException(final String message) {
        super(message);
    }

    public OSADbException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
