import static org.junit.Assert.assertFalse;

import com.toornament.ToornamentClient;
import com.toornament.concepts.Matches;
import com.toornament.model.Match;
import com.toornament.model.TournamentDetails;
import com.toornament.model.enums.Scope;
import com.toornament.model.request.MatchQuery;
import java.util.HashSet;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class MatchesTests {
    private ToornamentClient client;
    private Matches matches;
    private TournamentDetails details = new TournamentDetails();
    private HashSet<Scope> scopes = new HashSet<>();
    @Before
    public void Setup() {
        scopes.add(Scope.ORGANIZER_VIEW);
        client = new ToornamentClient(System.getenv("KEY"), System.getenv("CLIENT"), System.getenv("SECRET"), scopes);
        client.authorize();

        details.setId("906278647555784704");
        matches = new Matches(client,details);
    }

    @Test
    public void getMatchesTest(){
        List<Match> result = matches.getMatches(MatchQuery.builder().participantId(906379665349820416L).build(),"matches=0-127");

        assertFalse(result.isEmpty());

    }

//    @Test
//    public void getMatchV2Test(){
//        MatchDetails match = matchesV2.getMatch("989807940598333454");
//        assertEquals(987313091175768089L,match.getGroupNumber());
//        assertEquals(989807940564779021L,match.getRoundNumber());
//        assertEquals(987313089934254080L,match.getStageNumber());
//    }
}
