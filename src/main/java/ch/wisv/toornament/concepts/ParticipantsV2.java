package ch.wisv.toornament.concepts;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.exception.ToornamentException;
import ch.wisv.toornament.model.Participant;
import ch.wisv.toornament.model.TeamParticipant;
import ch.wisv.toornament.model.enums.Scope;
import okhttp3.HttpUrl;
import okhttp3.Request;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ParticipantsV2 extends Concept {
    private String tournamentID;
    public ParticipantsV2(ToornamentClient client, String tournamentID) {
        super(client);
        this.tournamentID = tournamentID;
    }
    public List<TeamParticipant> getTeamParticipants(Map<String,String> header, Map<String,String> paramsMap){
        Request request = getRequestHelper(header, paramsMap);
        return getTeamParticipantsHelper(request);
    }

    //Intended for getting participants of non-team games like Hearthstone or Mortal Kombat.
    public List<Participant> getParticipants(Map<String,String> header, Map<String,String> paramsMap){
        Request request = getRequestHelper(header, paramsMap);
        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class, Participant.class));
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            throw new ToornamentException("Got IOExcption getting Participants");
        }
    }

    private Request getRequestHelper(Map<String, String> header, Map<String, String> paramsMap) {
        HttpUrl.Builder url = new HttpUrl.Builder()
            .scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("viewer")
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("tournaments")
            .addEncodedPathSegment(tournamentID)
            .addEncodedPathSegment("participants");
        for (Map.Entry<String, String> params : paramsMap.entrySet()) {
            url.addQueryParameter(params.getKey(), params.getValue());
        }
        return client.getRequestBuilder()
            .get()
            .url(url.build())
            .addHeader("range", header.get("range"))
            .build();
    }

    public Participant getParticipant(String participantID){
        HttpUrl.Builder url = new HttpUrl.Builder()
            .scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("viewer")
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("tournaments")
            .addEncodedPathSegment(tournamentID)
            .addEncodedPathSegment("participants")
            .addEncodedPathSegment(participantID);

        Request request = client.getRequestBuilder()
            .get()
            .url(url.build())
            .build();
        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructType(TeamParticipant.class));
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            throw new ToornamentException("Got IOExcption getting Participants");
        }
    }

    //Uses the Participant API to get other participants associated with the current user token. Requires participant:manage_participations for the scope
    public List<TeamParticipant> getMyTeamParticipants(Map<String,String> header, Map<String,String> paramsMap) {
        HttpUrl.Builder url = new HttpUrl.Builder()
            .scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("participant")
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("me")
            .addEncodedPathSegment("participants");

        Request request = client.getRequestBuilder()
            .get()
            .url(url.build())
            .addHeader("range",header.get("range"))
            .build();

        return getTeamParticipantsHelper(request);
    }

    public TeamParticipant getTeamParticipantByID(String id){

        HttpUrl.Builder url = new HttpUrl.Builder();

        if(client.getScope().contains(Scope.MANAGE_PARTICIPANTS)){
        url
            .scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("participant")
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("me")
            .addEncodedPathSegment("participants")
            .addEncodedPathSegment(id);
        }
        else
            if(client.getScope().contains(Scope.ORGANIZER_PARTICIPANT)){
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
        Request request = client.getRequestBuilder()
            .get()
            .url(url.build())
            .build();

        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructType(TeamParticipant.class));
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            throw new ToornamentException("Got IOExcption getting Team Participants");
        }
    }

    private List<TeamParticipant> getTeamParticipantsHelper(Request request) {
        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody,
                mapper.getTypeFactory().constructCollectionType(List.class, TeamParticipant.class));
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            throw new ToornamentException("Got IOExcption getting Participants");
        }
    }
}
