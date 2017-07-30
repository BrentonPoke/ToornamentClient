package ch.wisv.toornament.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ToornamentError {

    @JsonProperty("message")
    String message;
    String scope;
    @JsonProperty("property_path")
    String propertyPath;
    @JsonProperty("invalid_value")
    String invalidValue;
    String type;
    @JsonProperty("error")
    String error;
    @JsonProperty("hint")
    String hint;
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getHint() {
        return this.hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
    
    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
