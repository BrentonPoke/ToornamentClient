package ch.wisv.toornament;

import ch.wisv.toornament.concepts.Disciplines;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import ch.wisv.toornament.concepts.Tournaments;
import ch.wisv.toornament.model.DisciplineDetails;
import ch.wisv.toornament.model.request.ApiTokenRequest;
import ch.wisv.toornament.model.response.ApiTokenResponse;
import java.text.SimpleDateFormat;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ToornamentClient {
    public static final MediaType JSON
        = MediaType.parse("application/json; charset=utf-8");
  
    private OkHttpClient httpClient;
    private String apiKey;
    private String clientId;
    private String clientSecret;
    private String oAuthToken;
    private ObjectMapper mapper;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public ToornamentClient(String apiKey, String clientId, String clientSecret) {
        this.apiKey = apiKey;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        httpClient = new OkHttpClient();
        mapper = new ObjectMapper();
        authorize();
    }

    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public Tournaments tournaments() {
        return new Tournaments(this);
    }
    
    public Disciplines disciplines() {
        return new Disciplines(this);
    }

    public void authorize() {
        ApiTokenRequest tokenRequest =
            new ApiTokenRequest("client_credentials", clientId, clientSecret);
        Request.Builder requestBuilder = new Request.Builder();
        try {
            
            
            requestBuilder.url("https://api.toornament.com/oauth/v2/token" 
                     + "?grant_type=" + tokenRequest.getGrantType()
                     + "&" + "client_id=" + tokenRequest.getClientId()
                     + "&" + "client_secret=" + tokenRequest.getClientSecret());
            Request request = requestBuilder.build();
            Response response = executeRequest(request);
            this.oAuthToken =
                mapper.readValue(response.body().string(), ApiTokenResponse.class).getAccessToken();
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
