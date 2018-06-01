package ch.wisv.toornament.concepts;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.exception.ToornamentException;
import ch.wisv.toornament.model.Stage;
import ch.wisv.toornament.model.Vod;
import ch.wisv.toornament.model.enums.ParticipantType;
import ch.wisv.toornament.model.Tournament;
import ch.wisv.toornament.model.TournamentDetails;
import ch.wisv.toornament.model.request.TournamentRequest;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static ch.wisv.toornament.ToornamentClient.JSON;
import com.fasterxml.jackson.databind.DeserializationFeature;
import java.util.Map;
import okhttp3.HttpUrl;
import okhttp3.internal.http.HttpHeaders;

public class Tournaments extends Concept {

    public Tournaments(ToornamentClient client) {
        super(client);
    }

    private List<Tournament> requestHelper(Request request){
        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class,
                Tournament.class));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ToornamentException("Couldn't retrieve tournaments");
        }
    }

    public List<Tournament> getAllTournaments() {
        Request request = client.getRequestBuilder()
            .get()
            .url("https://api.toornament.com/v1/tournaments")
            .build();
        return requestHelper(request);

    }

    public List<Tournament> getMyTournaments() {
        Request request = client.getAuthenticatedRequestBuilder()
            .get()
            .url("https://api.toornament.com/v1/me/tournaments")
            .build();
        return requestHelper(request);


    }
    public TournamentDetails getTournament(String id) {
        HttpUrl.Builder url = new HttpUrl.Builder();
        url.scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("v1")
            .addEncodedPathSegment("tournaments")
            .addEncodedPathSegment(id);
        Request request = client.getAuthenticatedRequestBuilder()
            .get()
            .url(url.build())
            .build();
        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody, TournamentDetails.class);
        } catch (IOException e) {
            e.getMessage();
            throw new ToornamentException("Couldn't get tournament with id: " + id);
        }
    }

    public List<Tournament> getTournamentByDiscipline(String discipline) {
        Request request = client.getAuthenticatedRequestBuilder()
            .get()
            .url("https://api.toornament.com/v1/tournaments" + "?discipline=" + discipline)
            .build();
        try {
            String responseBody = client.executeRequest(request).body().string();
            mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class,
                Tournament.class));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ToornamentException("Couldn't retrieve any tournaments under that discipline");
        }


    }

    public List<Tournament> getTournamentsWithParams(Map<String, String> paramsMap ) {
        HttpUrl.Builder url = new HttpUrl.Builder();
       url.scheme("https")
               .host("api.toornament.com")
               .addEncodedPathSegment("v1")
               .addEncodedPathSegment("tournaments");

        for (Map.Entry<String, String> params : paramsMap.entrySet()) {
            url.addQueryParameter(params.getKey(), params.getValue());
        }
        Request request = client.getRequestBuilder()
            .get()
            .url(url.build())
            .build();
        return requestHelper(request);

    }

    public List<Vod> getVods(String id){
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("category","replay");
        paramsMap.put("page","1");
        return getVods(id, paramsMap);
    }

    public List<Vod> getVods(String id, Map<String, String> paramsMap){
        HttpUrl.Builder url = new HttpUrl.Builder();
        url.scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("v1")
            .addEncodedPathSegment("tournaments")
            .addEncodedPathSegment(id)
            .addEncodedPathSegment("videos");

        for (Map.Entry<String, String> params : paramsMap.entrySet()) {
            url.addQueryParameter(params.getKey(), params.getValue());
        }
        Request request = client.getRequestBuilder()
            .get()
            .url(url.build())
            .build();
        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class,
                Vod.class));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ToornamentException("Couldn't retrieve vods for tournament with id "+ id);
        }

    }

    public List<Stage> getStages(String id){
        HttpUrl.Builder url = new HttpUrl.Builder();
        url.scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("v1")
            .addEncodedPathSegment("tournaments")
            .addEncodedPathSegment(id)
            .addEncodedPathSegment("stages");
        Request request = client.getRequestBuilder()
            .get()
            .url(url.build())
            .build();


        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class,
                Stage.class));
        } catch (IOException e) {
            e.getMessage();
        }
        return null;

    }


    public TournamentDetails createTournament(TournamentRequest tournamentRequest) {
        try {
            Request request = client.getAuthenticatedRequestBuilder()
                .post(RequestBody.create(JSON, mapper.writeValueAsString(tournamentRequest)))
                .url("https://api.toornament.com/v1/tournaments")
                .build();
            Response response = client.executeRequest(request);
            if (response.isSuccessful()) {
                return mapper.readValue(response.body().string(), TournamentDetails.class);
            } else {
                throw new ToornamentException("Couldn't create tournament" + response.body().string());
            }

        } catch (IOException | ToornamentException e) {
            e.printStackTrace();
            throw new ToornamentException("IOException occurred");
        }

    }

    public TournamentDetails createTournament(String discipline, String name, Integer size, ParticipantType participantType) {
        return createTournament(new TournamentRequest(discipline, name, size, participantType));
    }

    public TournamentDetails editTournament(String id, TournamentRequest tournamentRequest) {
        try {
            Request request = client.getAuthenticatedRequestBuilder()
                .patch(RequestBody.create(JSON, mapper.writeValueAsString(tournamentRequest)))
                .url("https://api.toornament.com/v1/tournaments/" + id)
                .build();
            Response response = client.executeRequest(request);
            if (response.isSuccessful()) {
                return mapper.readValue(response.body().string(), TournamentDetails.class);
            } else {
                throw new ToornamentException("Couldn't edit tournament" + response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ToornamentException("IOException occurred");
        }
    }

    public void deleteTournament(String id) {
        Request request = client.getAuthenticatedRequestBuilder()
            .delete()
            .url("https://api.toornament.com/v1/tournaments/" + id)
            .build();

        Response response = client.executeRequest(request);

        if (!response.isSuccessful()) {
            throw new ToornamentException("Could not delete tournament");
        }
    }
}
