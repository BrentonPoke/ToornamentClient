package ch.wisv.toornament.concepts;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.exception.ToornamentException;
import ch.wisv.toornament.model.Game;
import ch.wisv.toornament.model.Match;
import ch.wisv.toornament.model.enums.Scope;
import okhttp3.HttpUrl;
import okhttp3.HttpUrl.Builder;
import okhttp3.MediaType;
import okhttp3.Request;

import java.io.IOException;
import okhttp3.RequestBody;

public class MatchGamesV2 extends Concept {
    private String tournamentID;
    private String matchID;
    private HttpUrl.Builder urlBuilder;
    public MatchGamesV2(ToornamentClient client) {
        super(client);
    }
    public MatchGamesV2(ToornamentClient client, Match match){
        super(client);
        this.matchID = match.getId();
        this.tournamentID = match.getTournamentId();
    }

    public Game getGames(String number){
        getURL(number);
        return getGame(urlBuilder);
    }
    public Game getGames(){

        urlBuilder = new HttpUrl.Builder();
        if (client.getScope().contains(Scope.ORGANIZER_RESULT)) {

            urlBuilder
                .scheme("https")
                .host("api.toornament.com")
                .addPathSegment("viewer")
                .addPathSegment("v2")
                .addPathSegment("tournaments")
                .addPathSegment(tournamentID)
                .addPathSegment("matches")
                .addEncodedPathSegment(matchID)
                .addEncodedPathSegment("games");
        }
        return getGame(urlBuilder);
    }

    private Game getGame(Builder urlBuilder) {
        Request request = client.getAuthenticatedRequestBuilder()
            .get()
            .url(urlBuilder.build())
            .build();
        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper
                .readValue(responseBody, mapper.getTypeFactory().constructType(Game.class));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            throw new ToornamentException("Got IOExcption getting matches");
        }
    }

    public Game updateGame(String number, Game game){
        getURL(number);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),game.toString());
        Request request = client.getAuthenticatedRequestBuilder()
            .patch(requestBody)
            .url(urlBuilder.build())
            .build();
        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper
                .readValue(responseBody, mapper.getTypeFactory().constructType(Game.class));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            throw new ToornamentException("Got IOExcption getting matches");
        }
    }

    private void getURL(String number) {
        urlBuilder = new Builder();
        if (client.getScope().contains(Scope.ORGANIZER_RESULT)) {

            urlBuilder
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
        }
    }

}
