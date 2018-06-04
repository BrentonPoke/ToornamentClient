package ch.wisv.toornament.concepts;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.model.Game;
import ch.wisv.toornament.model.Match;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import ch.wisv.toornament.model.Opponent;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MatchGamesTest {
    private ToornamentClient client;
    private Match match;
    private MatchGamesV2 gamesV2;
    private ObjectMapper mapper;
    @Before
    public void setUp() {
        client = new ToornamentClient(System.getenv("KEY"), System.getenv("CLIENT"), System.getenv("SECRET"));
        client.authorize();
        mapper = new ObjectMapper();
        match = new Match();
        match.setId("989807940598333454");
        match.setTournamentId("906278647555784704");
        gamesV2 = new MatchGamesV2(client,match);
    }

    @Test
    public void getGames() throws IOException {
        Game game = gamesV2.getGames("3");
        List<Opponent> opponents = mapper.readValue("[\n" +
            "        {\n" +
            "            \"number\": 1,\n" +
            "            \"position\": 2,\n" +
            "            \"result\": \"loss\",\n" +
            "            \"forfeit\": false,\n" +
            "            \"score\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"number\": 2,\n" +
            "            \"position\": 1,\n" +
            "            \"result\": \"win\",\n" +
            "            \"forfeit\": false,\n" +
            "            \"score\": 2\n" +
            "        }\n" +
            "    ]",mapper.getTypeFactory().constructCollectionType(List.class, Opponent.class));
        System.out.println(opponents);
        System.out.println(game.getOpponents());
        assertTrue(game.getOpponents().equals(opponents));
    }
}
