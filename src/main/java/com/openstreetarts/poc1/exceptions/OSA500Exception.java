package com.openstreetarts.poc1.exceptions;

public class OSA500Exception extends OSAException {

    public OSA500Exception(final String message) {
        super(message);
    }

    public OSA500Exception(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
