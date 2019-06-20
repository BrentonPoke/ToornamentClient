package ch.wisv.toornament.concepts;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.exception.ToornamentException;
import ch.wisv.toornament.model.Participant;
import ch.wisv.toornament.model.Registration;
import ch.wisv.toornament.model.TeamParticipant;
import ch.wisv.toornament.model.enums.Scope;
import ch.wisv.toornament.model.enums.Sort;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RegistrationsV2 extends Concept {
  private String tournamentID;

  public RegistrationsV2(ToornamentClient client, String tournamentID) {
    super(client);
    this.tournamentID = tournamentID;
  }

  public TeamParticipant register(Participant participant) {
    HttpUrl.Builder url = new HttpUrl.Builder();
    if (client.getScope().contains(Scope.ORGANIZER_REGISTRATION)) {
      url.scheme("https")
          .host("api.toornament.com")
          .addEncodedPathSegment("organizer")
          .addEncodedPathSegment("v2")
          .addEncodedPathSegment("tournaments")
          .addEncodedPathSegment(tournamentID)
          .addEncodedPathSegment("registrations");
    }
    RequestBody body =
        RequestBody.create(MediaType.parse("application/json"), participant.toString());
    Request request = client.getRequestBuilder()
        .post(body)
        .url(url.build())
        .build();
    try {
      String responseBody = client.executeRequest(request).body().string();
      return mapper.readValue(responseBody, mapper.constructType(TeamParticipant.class));
    } catch (IOException | NullPointerException e) {
      System.out.println(e.getMessage());
      throw new ToornamentException("Got Excption posting Participant");
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
    return getTeamParticipantsHelper(request);
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
          .getRequestBuilder()
          .delete()
          .url(url.build())
          .build();
      return client.executeRequest(request).code();
  }

  private List<Registration> getTeamParticipantsHelper(Request request) {
    try {
      String responseBody = client.executeRequest(request).body().string();
      return mapper.readValue(
          responseBody,
          mapper.getTypeFactory().constructCollectionType(List.class, Registration.class));
    } catch (IOException | NullPointerException e) {
      System.out.println(e.getMessage());
      throw new ToornamentException("Got Excption getting Participants");
    }
  }
}
