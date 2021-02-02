package com.openstreetarts.poc1.exceptions;

public class OSA403Exception extends OSAException {

    public OSA403Exception(final String message) {
        super(message);
    }

    public OSA403Exception(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
