package ch.wisv.toornament.concepts;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.model.Tournament;
import ch.wisv.toornament.model.TournamentDetails;
import okhttp3.Request;

import java.io.IOException;
import java.util.List;

public class Tournaments extends Concept {

    public Tournaments(ToornamentClient client) {
        super(client);
    }

    public List<Tournament> getAllTournaments() throws IOException {
        Request request = client.getRequestBuilder().get().url("https://api.toornament.com/v1/tournaments").build();
        String responseBody = client.executeRequest(request).body().string();

        return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class, Tournament.class));
    }

    public List<Tournament> getMyTournaments() throws IOException {
        Request request = client.getAuthenticatedRequestBuilder().get().url("https://api.toornament.com/v1/me/tournaments").build();
        String responseBody = client.executeRequest(request).body().string();
        return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class, Tournament.class));

    }

    public TournamentDetails getTournament(String id) {

        return new TournamentDetails();

    }
}
