package ch.wisv.toornament.concepts;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.exception.ToornamentException;
import ch.wisv.toornament.model.Match;
import ch.wisv.toornament.model.MatchDetails;
import ch.wisv.toornament.model.MatchResult;
import ch.wisv.toornament.model.TournamentDetails;
import ch.wisv.toornament.model.request.MatchQueryBuilder;
import okhttp3.HttpUrl;
import okhttp3.Request;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Matches extends Concept {
    private TournamentDetails tournament;

    public Matches(ToornamentClient client, TournamentDetails tournament) {
        super(client);
        this.tournament = tournament;
    }

    public List<Match> getMatches() {
        return getMatches(new MatchQueryBuilder());
    }

    public List<Match> getMatches(MatchQueryBuilder builder) {
        Map<String, String> parameterMap = builder.build();
        try {
            HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
                .scheme("https")
                .host("api.toornament.com")
                .addPathSegment("v1")
                .addPathSegment("tournaments")
                .addPathSegment(tournament.getId())
                .addPathSegment("matches");

            for (Map.Entry<String, String> parameter : parameterMap.entrySet()) {
                urlBuilder.addQueryParameter(parameter.getKey(), parameter.getValue());
            }

            Request request = client.getAuthenticatedRequestBuilder()
                .get()
                .url(urlBuilder.build())
                .build();
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class, Match.class));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ToornamentException("Got IOExcption getting matches");
        }
    }

    public MatchDetails getMatch(String matchId, boolean withGames) {
        try {
            Request request = client.getAuthenticatedRequestBuilder()
                .get()
                .url("https://api.toornament.com/v1/tournaments/" + tournament.getId()
                    + "/matches/" + matchId
                    + "?with_games=" + (withGames ? "1" : "0"))
                .build();
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody, MatchDetails.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ToornamentException("Got IOException getting match with ID " + matchId);
        }
    }

    public MatchResult getResult(String matchId) {
        try {
            Request request = client.getAuthenticatedRequestBuilder()
                .get()
                .url("https://api.toornament.com/v1/tournaments/" + tournament.getId()
                    + "/matches/" + matchId
                    + "/result")
                .build();
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody, MatchResult.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ToornamentException("Got IOException getting match with ID " + matchId);
        }
    }
}
