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
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

public class MatchesTests {
    private ToornamentClient client, clientV1;
    private Matches matches;
    private MatchesV2 matchesV2;
    private TournamentDetails details = new TournamentDetails();

    @Before
    public void Setup() {
        client = new ToornamentClient(System.getenv("KEY"), System.getenv("CLIENT"), System.getenv("SECRET"), Scope.USER_INFO);
        client.authorize();
        clientV1 = new ToornamentClient(System.getenv("KEY"), System.getenv("CLIENT"), System.getenv("SECRET"));
        client.authorizeV1();

        details.setId("906278647555784704");
        matches = new Matches(clientV1,details);
        matchesV2 = new MatchesV2(client,details);
    }

    @Test
    public void getMatchesTest(){
        List<Match> result = matches.getMatches(new MatchQueryBuilder().participant("906379665349820416").withResult(true));
        System.out.println(result);
        assertFalse(result.isEmpty());

    }

    @Test
    public void getMatchesV2Test(){
        List<Match> result = client.matchesV2(details).getMatches(new MatchQueryBuilder().participant("906379665349820416"),"matches=0-127");

        assertFalse(result.isEmpty());

    }

    @Test
    public void getMatchTest() {
        MatchDetails details = matches.getMatch("989807940598333464",true);
        System.out.println(details);
        assertFalse(details.getGames().isEmpty());
        assertTrue(details.getId().matches("989807940598333464"));
        assertFalse(details.getVods().isEmpty());
    }

    @Test
    public void getResultTest() {
        MatchResult result = matches.getResult("989807940598333464");
        assertEquals(result.getStatus(),MatchStatus.COMPLETED);
        assertEquals(result.getOpponents().size(), 2);
    }

    @Test
    public void getMatchV2Test(){
        Match match = matchesV2.getMatch("989807940598333454");
        assertEquals(987313091175768089L,match.getGroupNumber());
        assertEquals(989807940564779021L,match.getRoundNumber());
        assertEquals(987313089934254080L,match.getStageNumber());
    }
}
