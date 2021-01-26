package com.openstreetarts.poc1.exceptions;

public abstract class OSAException extends Exception {

    public OSAException(final String message) {
        super(message);
    }

    public OSAException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
