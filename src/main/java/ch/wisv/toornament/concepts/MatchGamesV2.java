package ch.wisv.toornament.concepts;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.exception.ToornamentException;
import ch.wisv.toornament.model.Game;
import ch.wisv.toornament.model.Match;
import okhttp3.HttpUrl;
import okhttp3.Request;

import java.io.IOException;

public class MatchGamesV2 extends Concept {
    private String tournamentID;
    private String matchID;
    public MatchGamesV2(ToornamentClient client) {
        super(client);
    }
    public MatchGamesV2(ToornamentClient client, Match match){
        super(client);
        this.matchID = match.getId();
        this.tournamentID = match.getTournamentId();
    }

    public Game getGames(String number){
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
            .scheme("https")
            .host("api.toornament.com")
            .addPathSegment("viewer")
            .addPathSegment("v2")
            .addPathSegment("tournaments")
            .addPathSegment(tournamentID)
            .addPathSegment("matches")
            .addEncodedPathSegment(matchID)
            .addEncodedPathSegment("games")
            .addPathSegment(number);
        Request request = client.getAuthenticatedRequestBuilder()
            .get()
            .url(urlBuilder.build())
            .build();
        try{
        String responseBody = client.executeRequest(request).body().string();
        return mapper.readValue(responseBody, mapper.getTypeFactory().constructType(Game.class));
    } catch (IOException | NullPointerException e) {
        e.printStackTrace();
        throw new ToornamentException("Got IOExcption getting matches");
    }
    }

}
