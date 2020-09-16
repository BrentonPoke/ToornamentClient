import static org.junit.Assert.assertFalse;

import com.toornament.ToornamentClient;
import com.toornament.concepts.Matches;
import com.toornament.model.MatchDetails;
import com.toornament.model.TournamentDetails;
import com.toornament.model.enums.Scope;
import com.toornament.model.request.MatchQuery;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
        scopes.add(Scope.ORGANIZER_RESULT);
        client = new ToornamentClient(System.getenv("KEY"), System.getenv("CLIENT"), System.getenv("SECRET"), scopes);
        client.authorize();
        details.setId("3348419359666446336");
        matches = new Matches(client,details);
    }

    @Test
    public void getMatchesTest(){
        System.out.println(ZonedDateTime.parse("2020-03-06T05:57:28+05:00").format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")));
        List<MatchDetails> result = matches.getMatches(MatchQuery.builder()
            .scheduled(true)
            .scheduledAfter(ZonedDateTime.parse("2020-03-06T05:57:28+05:00"))// TODO: I will need to come back to this, because passing time values is unduly difficult as url parameters.
            .build(),"matches=0-67");

        System.out.println(result);

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
