package ch.wisv.toornament.concepts;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.exception.ToornamentException;
import ch.wisv.toornament.model.Group;
import ch.wisv.toornament.model.Participant;
import ch.wisv.toornament.model.TeamParticipant;
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
        Request request = client.getRequestBuilder()
            .get()
            .url(url.toString())
            .addHeader("range",header.get("range"))
            .build();
        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class, TeamParticipant.class));
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            throw new ToornamentException("Got IOExcption getting Participants");
        }
    }

    public List<Participant> getParticipants(Map<String,String> header, Map<String,String> paramsMap){
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
        Request request = client.getRequestBuilder()
            .get()
            .url(url.toString())
            .addHeader("range",header.get("range"))
            .build();
        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class, Participant.class));
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            throw new ToornamentException("Got IOExcption getting Participants");
        }
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
            .url(url.toString())
            .build();
        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructType(Participant.class));
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            throw new ToornamentException("Got IOExcption getting Participants");
        }
    }

    public TeamParticipant getTeamParticipant(String participantID){
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
            .url(url.toString())
            .build();
        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructType(TeamParticipant.class));
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            throw new ToornamentException("Got IOExcption getting Team Participants");
        }
    }
}
