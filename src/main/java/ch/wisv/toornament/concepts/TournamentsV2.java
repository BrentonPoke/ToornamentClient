package ch.wisv.toornament.concepts;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.exception.ToornamentException;
import ch.wisv.toornament.model.Stream;
import ch.wisv.toornament.model.Tournament;
import ch.wisv.toornament.model.TournamentDetails;
import okhttp3.HttpUrl;
import okhttp3.Request;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TournamentsV2 extends Concept {

    public TournamentsV2(ToornamentClient client) {
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

    public TournamentDetails getTournament(String id) {
        HttpUrl.Builder url = new HttpUrl.Builder();
        url.scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("viewer")
            .addEncodedPathSegment("v2")
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
            e.printStackTrace();
            throw new ToornamentException("Couldn't get tournament with id: " + id);
        }
    }

    public List<Stream> getStreams(String id, Map<String,String> range){
        HttpUrl.Builder url = new HttpUrl.Builder();
        url.scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("viewer")
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("tournaments")
            .addEncodedPathSegment(id)
            .addEncodedPathSegment("streams");
        Request request = client.getAuthenticatedRequestBuilder()
            .get()
            .url(url.build())
            .addHeader("range",range.get("range"))
            .build();
        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class,
                Stream.class));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ToornamentException("Couldn't retrieve streams from tournament with id "+ id);
        }

    }

}
