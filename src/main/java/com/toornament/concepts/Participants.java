package com.toornament.concepts;

import com.toornament.ToornamentClient;
import com.toornament.exception.ToornamentException;
import com.toornament.model.Participant;
import com.toornament.model.TeamParticipant;
import com.toornament.model.enums.Scope;
import com.toornament.model.request.ParticipantQuery;
import okhttp3.HttpUrl;
import okhttp3.HttpUrl.Builder;
import okhttp3.MediaType;
import okhttp3.Request;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import okhttp3.RequestBody;
import org.slf4j.LoggerFactory;

public class Participants extends Concept {
    private String tournamentID;
    public Participants(ToornamentClient client, String tournamentID) {
        super(client);
        this.tournamentID = tournamentID;
        logger = LoggerFactory.getLogger(this.getClass());
    }
    public List<TeamParticipant> getTeamParticipants(Map<String,String> header, ParticipantQuery parameters){
        Request request = getRequestHelper(header, parameters);
        return getTeamParticipantsHelper(request);
    }

    //Intended for getting participants of non-team games like Hearthstone or Mortal Kombat.
    public List<Participant> getParticipants(Map<String,String> header, ParticipantQuery parameters){
        Request request = getRequestHelper(header, parameters);
        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class, Participant.class));
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            throw new ToornamentException("Got IOException getting Participants");
        }
    }

    public Participant getParticipant(String participantID){
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
            .scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("viewer")
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("tournaments")
            .addEncodedPathSegment(tournamentID)
            .addEncodedPathSegment("participants")
            .addEncodedPathSegment(participantID);

        logger.debug("url: {}",urlBuilder.build().toString());

        Request request = client.getAuthenticatedRequestBuilder()
            .get()
            .url(urlBuilder.build())
            .build();

        return TeamParticipanthelper(request, "Got IOException getting Participants");
    }

    //Uses the Participant API to get other participants associated with the current user token. Requires participant:manage_participations for the scope

    public List<TeamParticipant> getMyTeamParticipants(Map<String,String> header, Map<String,String> paramsMap) {
        Builder urlBuilder = new Builder();
        logger.debug("Scopes: {}",client.getScope().toString());
    if (client.getScope().contains(Scope.MANAGE_PARTICIPANTS)) {
      urlBuilder.scheme("https")
          .host("api.toornament.com")
          .addEncodedPathSegment("participant")
          .addEncodedPathSegment("v2")
          .addEncodedPathSegment("me")
          .addEncodedPathSegment("participants");
            }
        for (Map.Entry<String, String> params : paramsMap.entrySet()) {
            urlBuilder.addQueryParameter(params.getKey(), params.getValue());
        }

        Request request = client.getRequestBuilder()
            .get()
            .url(urlBuilder.build())
            .addHeader("range",header.get("range"))
            .build();

        return getTeamParticipantsHelper(request);
    }
    public TeamParticipant getTeamParticipantByID(String id){

        Builder url = participantHelper(id);
        Request request = client.getRequestBuilder()
            .get()
            .url(url.build())
            .build();

        return TeamParticipanthelper(request, "Got IOException getting Team Participants");
    }

    public TeamParticipant updateParticipant(String id){
        Builder url = participantHelper(id);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"),"{ \"checked_in\": true }");
        Request request = client.getAuthenticatedRequestBuilder()
            .patch(body)
            .url(url.build())
            .build();

        return TeamParticipanthelper(request, "Got IOException getting Team Participants");

    }

    public TeamParticipant createParticipant(ParticipantQuery query){
        Builder url = new Builder();
        if (client.getScope().contains(Scope.ORGANIZER_PARTICIPANT)) {
            url
                .scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("organizer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("tournaments")
                .addEncodedPathSegment(tournamentID)
                .addEncodedPathSegment("participants");
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"),query.toString());
        Request request = client.getRequestBuilder()
            .post(body)
            .url(url.build())
            .build();
        return TeamParticipanthelper(request,"Error creating new Participant");
    }

    public Integer deleteParticipant(String id){
        Builder url = participantHelper(id);
        Request request = client.getAuthenticatedRequestBuilder()
            .delete()
            .url(url.build())
            .build();

        return client.executeRequest(request).code();
    }

    private Builder participantHelper(String id) {
        Builder url = new Builder();
        logger.debug("Scopes: {}",client.getScope().toString());
        if (client.getScope().contains(Scope.MANAGE_PARTICIPANTS)) {
            url
                .scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("participant")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("me")
                .addEncodedPathSegment("participants")
                .addEncodedPathSegment(id);
        } else if (client.getScope().contains(Scope.ORGANIZER_PARTICIPANT)) {
            url
                .scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("organizer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("tournaments")
                .addEncodedPathSegment(tournamentID)
                .addEncodedPathSegment("participants")
                .addEncodedPathSegment(id);
        }
        return url;
    }

    private TeamParticipant TeamParticipanthelper(Request request, String s) {
        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody,
                mapper.getTypeFactory().constructType(TeamParticipant.class));
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            throw new ToornamentException(s);
        }
    }

    private List<TeamParticipant> getTeamParticipantsHelper(Request request) {
        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody,
                mapper.getTypeFactory().constructCollectionType(List.class, TeamParticipant.class));
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            throw new ToornamentException("Got IOException getting Participants");
        }
    }

    private Request getRequestHelper(Map<String, String> header, ParticipantQuery parameters) {
        HttpUrl.Builder url = new HttpUrl.Builder()
            .scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("viewer")
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("tournaments")
            .addEncodedPathSegment(tournamentID)
            .addEncodedPathSegment("participants");

        url.addQueryParameter("name",parameters.getName());
        url.addQueryParameter("sort",parameters.getSort().toString());

        return client.getRequestBuilder()
            .get()
            .url(url.build())
            .addHeader("range", header.get("range"))
            .build();
    }
}
