package com.toornament.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ToornamentException extends RuntimeException {
    String error;
    String message;
    String hint;
    public ToornamentException(String message) {
        super(message);
    }
}
