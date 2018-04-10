package ch.wisv.toornament.concepts;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.exception.ToornamentException;
import ch.wisv.toornament.model.ParticipantType;
import ch.wisv.toornament.model.Tournament;
import ch.wisv.toornament.model.TournamentDetails;
import ch.wisv.toornament.model.request.TournamentRequest;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
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

    public List<Tournament> getFeaturedTournaments(Map<String, String> paramsMap, Map<String,String> header) {
        HttpUrl.Builder url = new HttpUrl.Builder();
        url.scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("viewer")
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("tournaments")
            .addEncodedPathSegment("featured");
        for (Map.Entry<String, String> params : paramsMap.entrySet()) {
            url.addQueryParameter(params.getKey(), params.getValue());
        }
        Request request = client.getRequestBuilder()
            .get()
            .url(url.build())
            .addHeader("range",header.get("range"))
            .build();
        return requestHelper(request);

    }

    public List<Tournament> getMyTournaments() {
        Request request = client.getAuthenticatedRequestBuilder()
            .get()
            .url("https://api.toornament.com/viewer/v2/me/tournaments")
            .build();
        return requestHelper(request);


    }

    public List<Tournament> getTournamentByDiscipline(String discipline) {
        Request request = client.getAuthenticatedRequestBuilder()
            .get()
            .url("https://api.toornament.com/viewer/v2/tournaments" + "?discipline=" + discipline)
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
           .addEncodedPathSegment("viewer")
               .addEncodedPathSegment("v2")
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
    public TournamentDetails getTournament(String id) {
        Request request = client.getAuthenticatedRequestBuilder()
            .get()
            .url("https://api.toornament.com/viewer/v2/tournaments/" + id)
            .build();
        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody, TournamentDetails.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ToornamentException("Couldn't get tournament with id: " + id);
        }
    }

    public TournamentDetails createTournament(TournamentRequest tournamentRequest) {
        try {
            Request request = client.getAuthenticatedRequestBuilder()
                .post(RequestBody.create(JSON, mapper.writeValueAsString(tournamentRequest)))
                .url("https://api.toornament.com/viewer/v2/tournaments")
                .build();
            Response response = client.executeRequest(request);
            if (response.isSuccessful()) {
                return mapper.readValue(response.body().string(), TournamentDetails.class);
            } else {
                throw new ToornamentException("Couldn't create tournament" + response.body().string());
            }

        } catch (IOException e) {
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
                .url("https://api.toornament.com/v2/tournaments/" + id)
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
            .url("https://api.toornament.com/v2/tournaments/" + id)
            .build();

        Response response = client.executeRequest(request);

        if (!response.isSuccessful()) {
            throw new ToornamentException("Could not delete tournament");
        }
    }
}
