package ch.wisv.toornament.model.request;

import ch.wisv.toornament.model.enums.Scope;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ApiTokenRequest {

    @JsonProperty("grant_type")
    String grantType;
    @JsonProperty("client_id")
    String clientId;
    @JsonProperty("client_secret")
    String clientSecret;
    @JsonProperty("scope")
    String scope;

    public ApiTokenRequest(String grantType, String clientId, String clientSecret, Scope scope) {
        this.grantType = grantType;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.scope = scope.toString();
    }
}
