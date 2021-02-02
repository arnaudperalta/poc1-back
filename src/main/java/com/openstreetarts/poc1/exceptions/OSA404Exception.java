package com.openstreetarts.poc1.exceptions;

public class OSA404Exception extends OSAException {

    public OSA404Exception(final String message) {
        super(message);
    }

    public OSA404Exception(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
