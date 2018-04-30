package ch.wisv.toornament.concepts;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.exception.ToornamentException;
import ch.wisv.toornament.model.Match;
import ch.wisv.toornament.model.MatchDetails;
import ch.wisv.toornament.model.TournamentDetails;
import ch.wisv.toornament.model.request.MatchQueryBuilder;
import okhttp3.HttpUrl;
import okhttp3.Request;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MatchesV2 extends Concept {
    private TournamentDetails tournament;
    public MatchesV2(ToornamentClient client, TournamentDetails tournament) {
        super(client);
        this.tournament = tournament;
    }

    public List<Match> getMatches(MatchQueryBuilder builder,String headers) {
        Map<String, String> parameterMap = builder.build();
        try {
            HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
                .scheme("https")
                .host("api.toornament.com")
                .addPathSegment("viewer")
                .addPathSegment("v2")
                .addPathSegment("tournaments")
                .addPathSegment(tournament.getId())
                .addPathSegment("matches");

            for (Map.Entry<String, String> parameter : parameterMap.entrySet()) {
                urlBuilder.addQueryParameter(parameter.getKey(), parameter.getValue());
            }

            Request request = client.getAuthenticatedRequestBuilder()
                .get()
                .url(urlBuilder.toString())
                .addHeader("Range",headers)
                .build();
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class, Match.class));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ToornamentException("Got IOExcption getting matches");
        }
    }

    public Match getMatch(String tournamentId, String matchId){
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
            .scheme("https")
            .host("api.toornament.com")
            .addPathSegment("viewer")
            .addPathSegment("v2")
            .addPathSegment("tournaments")
            .addPathSegment(tournamentId)
            .addPathSegment("matches")
            .addPathSegment(matchId);
        Request request = client.getRequestBuilder().get().url(urlBuilder.toString()).build();
        try {

            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody,mapper.getTypeFactory().constructType(Match.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

}
