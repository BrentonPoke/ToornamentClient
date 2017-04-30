package ch.wisv.toornament.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiTokenRequest {

    @JsonProperty("grant_type")
    String grantType;
    @JsonProperty("client_id")
    String clientId;
    @JsonProperty("client_secret")
    String clientSecret;

    public ApiTokenRequest(String grantType, String clientId, String clientSecret) {
        this.grantType = grantType;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
}
