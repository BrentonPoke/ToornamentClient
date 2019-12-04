package com.toornament.exception;

public class ToornamentException extends RuntimeException {
    String error;
    String message;
    String hint;
    public ToornamentException(String message) {
        super(message);
    }
}
