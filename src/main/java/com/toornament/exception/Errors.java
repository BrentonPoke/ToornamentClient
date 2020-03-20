
package com.toornament.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
@SuppressWarnings("unused")
public class Errors {
    private List<Error> errors;
    @NoArgsConstructor
    @Setter
    @Getter
    static class Error {
        @JsonProperty("invalid_value")
    private String invalidValue;
    private String message;
    @JsonProperty("property_path")
    private String propertyPath;
    private String scope;
}
@Override
    public String toString() {
        try {
            return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS).writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
