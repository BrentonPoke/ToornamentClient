import com.toornament.ToornamentClient;
import com.toornament.concepts.MatchGames;
import com.toornament.model.Match;
import com.toornament.model.enums.Scope;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashSet;
import org.junit.Before;

public class MatchGamesTest {
    private ToornamentClient client;
    private Match match;
    private MatchGames gamesV2;
    private ObjectMapper mapper;
    private HashSet<Scope> scopes = new HashSet<>();
    @Before
    public void setUp() {
        scopes.add(Scope.ORGANIZER_VIEW);
        client = new ToornamentClient(System.getenv("KEY"), System.getenv("CLIENT"), System.getenv("SECRET"), scopes);

        mapper = new ObjectMapper();
        match = new Match();
        match.setId("989807940598333454");
        match.setTournamentId("906278647555784704");
        gamesV2 = new MatchGames(client,match);
    }

//    @Test
//    public void getGames() throws IOException {
//        Game game = gamesV2.getGames("3");
//        //Just thought i'd try it
//        List<Opponent> opponents = mapper.readValue("[\n" +
//            "        {\n" +
//            "            \"number\": 1,\n" +
//            "            \"position\": 2,\n" +
//            "            \"result\": \"loss\",\n" +
//            "            \"forfeit\": false,\n" +
//            "            \"score\": 1\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"number\": 2,\n" +
//            "            \"position\": 1,\n" +
//            "            \"result\": \"win\",\n" +
//            "            \"forfeit\": false,\n" +
//            "            \"score\": 2\n" +
//            "        }\n" +
//            "    ]",mapper.getTypeFactory().constructCollectionType(List.class, Opponent.class));
//        System.out.println(opponents);
//        System.out.println(game.getOpponents());
//        assertEquals(game.getOpponents(),opponents);
//    }
}
