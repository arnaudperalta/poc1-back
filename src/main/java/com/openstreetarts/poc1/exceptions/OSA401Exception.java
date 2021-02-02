package com.openstreetarts.poc1.exceptions;

public class OSA401Exception extends OSAException {

    public OSA401Exception(final String message) {
        super(message);
    }

    public OSA401Exception(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
