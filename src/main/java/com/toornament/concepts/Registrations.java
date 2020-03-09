package com.toornament.concepts;

import com.toornament.ToornamentClient;
import com.toornament.exception.ToornamentException;
import com.toornament.model.Registration;
import com.toornament.model.enums.Scope;
import com.toornament.model.enums.Sort;
import com.toornament.model.request.RegistrationQuery;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import okhttp3.HttpUrl;
import okhttp3.HttpUrl.Builder;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

public class Registrations extends Concept {
  private String tournamentID;

  public Registrations(ToornamentClient client, String tournamentID) {
    super(client);
    this.tournamentID = tournamentID;
    logger = LoggerFactory.getLogger(this.getClass());
  }

  public Registration register(RegistrationQuery registration) {
    HttpUrl.Builder urlBuilder = new HttpUrl.Builder();

    logger.debug("Scopes {}",client.getScope());
    if (client.getScope().contains(Scope.ORGANIZER_REGISTRATION)) {

      urlBuilder.scheme("https")
          .host("api.toornament.com")
          .addEncodedPathSegment("organizer")
          .addEncodedPathSegment("v2")
          .addEncodedPathSegment("tournaments")
          .addEncodedPathSegment(tournamentID)
          .addEncodedPathSegment("registrations");
    } else if (client.getScope().contains(Scope.MANAGE_REGISTRATIONS)){
        urlBuilder.scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("participant")
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("me")
            .addEncodedPathSegment("registrations");

    }

      logger.debug("url: {}",urlBuilder.build().toString());

    RequestBody body =
        RequestBody.create(MediaType.parse("application/json"), registration.toString());
    Request request = client.getRequestBuilder()
        .post(body)
        .url(urlBuilder.build())
        .build();
    try {
      String responseBody = client.executeRequest(request).body().string();
      return mapper.readValue(responseBody, mapper.constructType(Registration.class));
    } catch (IOException | NullPointerException e) {
      System.out.println(e.getMessage());
      throw new ToornamentException("Got Exception posting Participant");
    }
  }
  public Registration updateRegistration(String id, Registration registration){
      Builder url = getURL(id);

      RequestBody body =
          RequestBody.create(MediaType.parse("application/json"), registration.toString());
      Request request =
          client
              .getAuthenticatedRequestBuilder()
              .patch(body)
              .url(url.build())
              .build();
      try {
          String responseBody = client.executeRequest(request).body().string();
          return mapper.readValue(responseBody, mapper.constructType(Registration.class));
      } catch (IOException | NullPointerException e) {
          System.out.println(e.getMessage());
          throw new ToornamentException("Got Exception updating Participant");
      }
  }
  public List<Registration> getRegistrations(
        Map<String, String> header){
      return getRegistrations(header, Sort.ASCENDING);
    }

  public List<Registration> getRegistrations(
      Map<String, String> header, Sort sort) {
    HttpUrl.Builder url = new HttpUrl.Builder();
    if (client.getScope().contains(Scope.ORGANIZER_REGISTRATION)) {
      url.scheme("https")
          .host("api.toornament.com")
          .addEncodedPathSegment("organizer")
          .addEncodedPathSegment("v2")
          .addEncodedPathSegment("tournaments")
          .addEncodedPathSegment(tournamentID)
          .addEncodedPathSegment("registrations");

        url.addQueryParameter("sort",sort.name());
    }
    Request request =
        client
            .getRequestBuilder()
            .get()
            .url(url.build())
            .addHeader("range", header.get("range"))
            .build();
    return getRegistrationsHelper(request);
  }
  public Registration getRegistration(String id){
      Builder url = getURL(id);

      Request request =
          client
              .getAuthenticatedRequestBuilder()
              .get()
              .url(url.build())
              .build();
      try {
          String responseBody = client.executeRequest(request).body().string();
          return mapper.readValue(responseBody, mapper.constructType(Registration.class));
      } catch (IOException | NullPointerException e) {
          System.out.println(e.getMessage());
          throw new ToornamentException("Got Exception posting Participant");
      }
  }

    private Builder getURL(String id) {
        Builder url = new Builder();
        if (client.getScope().contains(Scope.ORGANIZER_REGISTRATION)) {
            url.scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("organizer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("tournaments")
                .addEncodedPathSegment(tournamentID)
                .addEncodedPathSegment("registrations")
                .addEncodedPathSegment(id);
        } else if (client.getScope().contains(Scope.MANAGE_REGISTRATIONS)) {
            url.scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("participant")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("me")
                .addEncodedPathSegment("registrations")
                .addEncodedPathSegment(id);

        }
        return url;
    }

    public int deleteRegistration(String id){
      HttpUrl.Builder url = new HttpUrl.Builder();
    if (client.getScope().contains(Scope.ORGANIZER_REGISTRATION)) {
      url.scheme("https")
          .host("api.toornament.com")
          .addEncodedPathSegment("organizer")
          .addEncodedPathSegment("v2")
          .addEncodedPathSegment("tournaments")
          .addEncodedPathSegment(tournamentID)
          .addEncodedPathSegment("registrations")
          .addEncodedPathSegment(id);
          }
      Request request =
          client
          .getAuthenticatedRequestBuilder()
          .delete()
          .url(url.build())
          .build();
      return client.executeRequest(request).code();
  }
  public List<Registration> getMyRegistrations(Map<String, String> header, List<Long> tournamentIDs, List<Long> playlistIDs){
      HttpUrl.Builder url = new HttpUrl.Builder();
      if (client.getScope().contains(Scope.MANAGE_REGISTRATIONS)) {
          url.scheme("https")
              .host("api.toornament.com")
              .addEncodedPathSegment("participant")
              .addEncodedPathSegment("v2")
              .addEncodedPathSegment("me")
              .addEncodedPathSegment("registrations");

          url.addQueryParameter("tournament_ids", StringUtils.join(tournamentIDs,","))
              .addQueryParameter("playlist_ids",StringUtils.join(playlistIDs,","));
      }

      Request request =
          client
              .getAuthenticatedRequestBuilder()
              .get()
              .url(url.build())
              .addHeader("range", header.get("range"))
              .build();
        return getRegistrationsHelper(request);
  }
  private List<Registration> getRegistrationsHelper(Request request) {
    try {
      String responseBody = client.executeRequest(request).body().string();
      return mapper.readValue(
          responseBody,
          mapper.getTypeFactory().constructCollectionType(List.class, Registration.class));
    } catch (IOException | NullPointerException e) {
      System.out.println(e.getMessage());
      throw new ToornamentException("Got Exception getting Participants");
    }
  }
}
