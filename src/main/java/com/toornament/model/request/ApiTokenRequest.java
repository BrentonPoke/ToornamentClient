package com.toornament.model.request;

import com.toornament.model.enums.Scope;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;
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
    String scope="";

    public ApiTokenRequest(String grantType, String clientId, String clientSecret, HashSet<Scope> scopes) {
        this.grantType = grantType;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.scope = stringify(scopes);
    }

    private String stringify(HashSet<Scope> scopeHashSet){
        for (Scope s : scopeHashSet ) {
            scope = scope.concat(s.toString()+" ");
        }
        return scope;
    }

}
