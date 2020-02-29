import com.toornament.ToornamentClient;
import com.toornament.concepts.Matches;
import com.toornament.model.TournamentDetails;
import com.toornament.model.enums.Scope;
import java.util.HashSet;
import org.junit.Before;

public class MatchesTests {
    private ToornamentClient client;
    private Matches matches;
    private TournamentDetails details = new TournamentDetails();
    private HashSet<Scope> scopes = new HashSet<>();
    @Before
    public void Setup() {
        scopes.add(Scope.ORGANIZER_RESULT);
        client = new ToornamentClient(System.getenv("KEY"), System.getenv("CLIENT"), System.getenv("SECRET"), scopes);
        client.authorize();

        details.setId("906278647555784704");
        matches = new Matches(client,details);
    }

//    @Test
//    public void getMatchesTest(){
//        System.out.println(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX",
//            Locale.ENGLISH)));
//        List<Match> result = matches.getMatches(MatchQuery.builder().participantId(906379665349820416L)
//            .scheduledBefore(ZonedDateTime.now())
//            .scheduledAfter(ZonedDateTime.now().minusYears(3L))
//            .build(),"matches=0-127");
//
//        System.out.println(result);
//
//        assertFalse(result.isEmpty());
//
//    }

//    @Test
//    public void getMatchV2Test(){
//        MatchDetails match = matchesV2.getMatch("989807940598333454");
//        assertEquals(987313091175768089L,match.getGroupNumber());
//        assertEquals(989807940564779021L,match.getRoundNumber());
//        assertEquals(987313089934254080L,match.getStageNumber());
//    }
}
