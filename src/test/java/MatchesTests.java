import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.concepts.Matches;
import ch.wisv.toornament.model.Match;
import ch.wisv.toornament.model.TournamentDetails;
import ch.wisv.toornament.model.request.MatchQueryBuilder;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
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

        assertFalse(result.isEmpty());

    }
}
