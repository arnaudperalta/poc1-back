package com.openstreetarts.poc1.exceptions;

public class OSA400Exception extends OSAException {

    public OSA400Exception(final String message) {
        super(message);
    }

    public OSA400Exception(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
