package com.toornament;

import com.toornament.concepts.Disciplines;
import com.toornament.concepts.FinalStandings;
import com.toornament.concepts.Groups;
import com.toornament.concepts.MatchGames;
import com.toornament.concepts.MatchReports;
import com.toornament.concepts.Matches;
import com.toornament.concepts.Permissions;
import com.toornament.concepts.Registrations;
import com.toornament.concepts.Rounds;
import com.toornament.concepts.Streams;
import com.toornament.concepts.Tournaments;
import com.toornament.concepts.Users;
import com.toornament.concepts.Webhooks;
import com.toornament.model.TournamentDetails;
import com.toornament.model.enums.Scope;
import com.toornament.model.request.ApiTokenRequest;
import com.toornament.model.response.ApiTokenResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.HashSet;
import lombok.Getter;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToornamentClient {
  public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

  private OkHttpClient httpClient;
  private String apiKey;
  private String clientId;
  private String clientSecret;
  private String oAuthToken;
  private HashSet<Scope> scope;
  private ObjectMapper mapper;
  public ToornamentClient(
      String apiKey, String clientId, String clientSecret, HashSet<Scope> scope) {
    this.apiKey = apiKey;
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.scope = scope;
    this.httpClient = new OkHttpClient();
    mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    authorize();
  }
    Logger logger = LoggerFactory.getLogger(this.getClass());

  public HashSet<Scope> getScope() {
    return scope;
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

  public Matches matches(TournamentDetails tournament) {
    return new Matches(this, tournament);
  }

  public Groups groups(String tournamentId) {
    return new Groups(this, tournamentId);
  }

  public FinalStandings finalStandings() {
    return new FinalStandings(this);
  }

  public MatchGames matchGames() {
    return new MatchGames(this);
  }

  public Permissions permissions(String tournamentID) {
    return new Permissions(this, tournamentID);
  }

  public Registrations registrations(String tournamentID) {
    return new Registrations(this, tournamentID);
  }

  public MatchReports Reports(String tournamentID) {
    return new MatchReports(this, tournamentID);
  }

  public Rounds rounds(String tournamentID) {
    return new Rounds(this, tournamentID);
  }

  public Streams streams(String tournamentID) {
    return new Streams(this, tournamentID);
  }

  public Users users() {
    return new Users(this);
  }

  public Webhooks Webhooks() {
    return new Webhooks(this);
  }

  public void authorize() {
    ApiTokenRequest tokenRequest =
        new ApiTokenRequest("client_credentials", clientId, clientSecret, scope);
    Request.Builder requestBuilder = new Request.Builder();
    try {
      requestBuilder.url(
          "https://api.toornament.com/oauth/v2/token"
              + "?grant_type="
              + tokenRequest.getGrantType()
              + "&"
              + "client_id="
              + tokenRequest.getClientId()
              + "&"
              + "client_secret="
              + tokenRequest.getClientSecret()
              + "&"
              + "scope="
              + tokenRequest.getScope());
      Request request = requestBuilder.build();
      Response response = executeRequest(request);
      this.oAuthToken =
          mapper.readValue(response.body().string(), ApiTokenResponse.class).getAccessToken();
    } catch (IOException e) {

      logger.error("Issue authorizing client: {}",e.getMessage());
    }
  }

  public Request.Builder getAuthenticatedRequestBuilder() {
    if (this.oAuthToken == null) {
      authorize();
    }
    return getRequestBuilder().addHeader("Authorization", "Bearer " + oAuthToken);
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
