package ch.wisv.toornament.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ToornamentError {

    String message;
    String scope;
    @JsonProperty("property_path")
    String propertyPath;
    @JsonProperty("invalid_value")
    String invalidValue;
    String type;
}
