import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.concepts.Matches;
import ch.wisv.toornament.concepts.MatchesV2;
import ch.wisv.toornament.model.Match;
import ch.wisv.toornament.model.MatchDetails;
import ch.wisv.toornament.model.MatchResult;
import ch.wisv.toornament.model.TournamentDetails;
import ch.wisv.toornament.model.enums.MatchStatus;
import ch.wisv.toornament.model.enums.Scope;
import ch.wisv.toornament.model.request.MatchQueryBuilder;
import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

public class MatchesTests {
    private ToornamentClient client;
    private MatchesV2 matchesV2;
    private TournamentDetails details = new TournamentDetails();
    private HashSet<Scope> scopes = new HashSet<>();
    @Before
    public void Setup() {
        scopes.add(Scope.ORGANIZER_VIEW);
        client = new ToornamentClient(System.getenv("KEY"), System.getenv("CLIENT"), System.getenv("SECRET"), scopes);
        client.authorize();

        details.setId("906278647555784704");
        matchesV2 = new MatchesV2(client,details);
    }

//    @Test
//    public void getMatchesV2Test(){
//        List<Match> result = matchesV2.getMatches(new MatchQueryBuilder().participant("906379665349820416"),"matches=0-127");
//
//        assertFalse(result.isEmpty());
//
//    }

    @Test
    public void getMatchV2Test(){
        MatchDetails match = matchesV2.getMatch("989807940598333454");
        assertEquals(987313091175768089L,match.getGroupNumber());
        assertEquals(989807940564779021L,match.getRoundNumber());
        assertEquals(987313089934254080L,match.getStageNumber());
    }
}
