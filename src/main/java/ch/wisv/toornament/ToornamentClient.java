package ch.wisv.toornament;

import ch.wisv.toornament.concepts.Tournaments;
import ch.wisv.toornament.model.request.ApiTokenRequest;
import ch.wisv.toornament.model.response.ApiTokenResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import okhttp3.*;

import java.io.IOException;

public class ToornamentClient {
    @Getter
    private OkHttpClient httpClient;

    private String apiKey;
    private String clientId;
    private String clientSecret;

    private String oAuthToken;

    @Getter
    private ObjectMapper mapper;

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public ToornamentClient(String apiKey, String clientId, String clientSecret) {
        this.apiKey = apiKey;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        httpClient = new OkHttpClient();
        mapper = new ObjectMapper();
        authorize();
    }

    public Tournaments tournaments() {
        return new Tournaments(this);
    }

    public void authorize() {
        ApiTokenRequest tokenRequest = new ApiTokenRequest("client_credentials", clientId, clientSecret);
        Request.Builder requestBuilder = new Request.Builder();
        try {
            RequestBody body = RequestBody.create(JSON, mapper.writeValueAsString(tokenRequest));
            requestBuilder.url("https://api.toornament.com/oauth/v2/token").post(body);
            Request request = requestBuilder.build();
            Response response = executeRequest(request);
            this.oAuthToken = mapper.readValue(response.body().string(), ApiTokenResponse.class).getAccessToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Request.Builder getAuthenticatedRequestBuilder() {
        if (this.oAuthToken == null) {
            authorize();
        }
        return getRequestBuilder()
                .addHeader("Authorization", "Bearer " + oAuthToken);
    }

    public Request.Builder getRequestBuilder() {
        return new Request.Builder().addHeader("X-Api-Key", apiKey);
    }

    public Response executeRequest(Request request) {
        try {
            return this.httpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
