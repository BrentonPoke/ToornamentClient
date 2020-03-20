package com.toornament.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ToornamentException extends RuntimeException {
    String message;
    String hint;
    public ToornamentException(String message) {
        super(message);
    }
}
