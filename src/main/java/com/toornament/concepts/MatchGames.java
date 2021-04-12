package com.toornament.concepts;

import com.toornament.ToornamentClient;
import com.toornament.exception.ToornamentException;
import com.toornament.model.Game;
import com.toornament.model.Match;
import com.toornament.model.enums.Scope;
import java.util.List;
import okhttp3.HttpUrl;
import okhttp3.HttpUrl.Builder;
import okhttp3.MediaType;
import okhttp3.Request;

import java.io.IOException;
import okhttp3.RequestBody;
import org.slf4j.LoggerFactory;

public class MatchGames extends Concept {
    private String tournamentID;
    private String matchID;
    private HttpUrl.Builder urlBuilder;
    public MatchGames(ToornamentClient client) {
        super(client);
    }
    public MatchGames(ToornamentClient client, Match match){
        super(client);
        this.matchID = match.getId();
        this.tournamentID = match.getTournamentId();
        logger = LoggerFactory.getLogger(this.getClass());
    }

    public Game getGame(String number){
        getURL(number);
        return getGameHelper(urlBuilder);
    }
    public List<Game> getGames(){

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

        logger.debug("url: {}",urlBuilder.build().toString());

        Request request = client.getRequestBuilder()
            .get()
            .url(urlBuilder.build())
            .build();

        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper
                .readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class,Game.class));
        } catch (IOException | NullPointerException e) {
            logger.error(e.getMessage());
            throw new ToornamentException("Got IOException getting games");
        }
    }

    private Game getGameHelper(Builder urlBuilder) {
        Request request = client.getAuthenticatedRequestBuilder()
            .get()
            .url(urlBuilder.build())
            .build();
        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper
                .readValue(responseBody, mapper.getTypeFactory().constructType(Game.class));
        } catch (IOException | NullPointerException e) {
            logger.error(e.getMessage());
            throw new ToornamentException("Got IOExcption getting game");
        }
    }

    public Game updateGame(String number, Game game){
        getURL(number);
        RequestBody requestBody = RequestBody.create(ToornamentClient.JSON,game.toString());
        Request request = client.getAuthenticatedRequestBuilder()
            .patch(requestBody)
            .url(urlBuilder.build())
            .build();
        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper
                .readValue(responseBody, mapper.getTypeFactory().constructType(Game.class));
        } catch (IOException | NullPointerException e) {
            logger.error(e.getMessage());
            throw new ToornamentException("Got IOException updating game");
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
