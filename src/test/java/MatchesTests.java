import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.concepts.Matches;
import ch.wisv.toornament.model.Match;
import ch.wisv.toornament.model.MatchDetails;
import ch.wisv.toornament.model.MatchResult;
import ch.wisv.toornament.model.TournamentDetails;
import ch.wisv.toornament.model.enums.MatchStatus;
import ch.wisv.toornament.model.request.MatchQueryBuilder;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

public class MatchesTests {
    private ToornamentClient client;
    private Matches matches;
    private TournamentDetails details = new TournamentDetails();

    @Before
    public void Setup() throws IOException {
        client = new ToornamentClient(System.getenv("KEY"), System.getenv("CLIENT"), System.getenv("SECRET"));
        client.authorize();

        details.setId("906278647555784704");
        matches = new Matches(client,details);
    }

    @Test
    public void getMatchesTest(){
        List<Match> result = matches.getMatches(new MatchQueryBuilder().participant("906379665349820416"));
        for(Match m : result)
            System.out.println(m.getDate());
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
}
