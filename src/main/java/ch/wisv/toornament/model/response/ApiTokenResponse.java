package ch.wisv.toornament.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApiTokenResponse {

    @JsonProperty("access_token")
    String accessToken;
    @JsonProperty("expires_in")
    String expiresIn;
    @JsonProperty("token_type")
    String tokenType;
    String scope;

}
