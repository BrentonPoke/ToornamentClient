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

public class Tournaments extends Concept {

    public Tournaments(ToornamentClient client) {
        super(client);
    }

    public List<Tournament> getAllTournaments() throws IOException {
        Request request = client.getRequestBuilder()
            .get()
            .url("https://api.toornament.com/v1/tournaments")
            .build();
        String responseBody = client.executeRequest(request).body().string();

        return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class, Tournament.class));
    }

    public List<Tournament> getMyTournaments() throws IOException {
        Request request = client.getAuthenticatedRequestBuilder()
            .get()
            .url("https://api.toornament.com/v1/me/tournaments")
            .build();
        String responseBody = client.executeRequest(request).body().string();
        return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class, Tournament.class));

    }

    public TournamentDetails getTournament(String id) throws IOException {
        Request request = client.getAuthenticatedRequestBuilder()
            .get()
            .url("https://api.toornament.com/v1/tournaments/" + id)
            .build();
        String responseBody = client.executeRequest(request).body().string();

        return mapper.readValue(responseBody, TournamentDetails.class);
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
