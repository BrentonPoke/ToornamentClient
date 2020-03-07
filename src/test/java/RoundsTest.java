import com.toornament.ToornamentClient;
import com.toornament.concepts.Rounds;
import com.toornament.model.Round;
import com.toornament.model.enums.Scope;
import com.toornament.model.request.RoundsQuery;
import com.toornament.model.request.RoundsQuery.RoundsQueryBuilder;
import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

 public class RoundsTest {
    private ToornamentClient client;
     private HashSet<Scope> scopes = new HashSet<>();
    private Rounds rounds;
    @Before
    public void Setup() throws IOException {
        scopes.add(Scope.ORGANIZER_ADMIN);
        client = new ToornamentClient(System.getenv("KEY"), System.getenv("CLIENT"), System.getenv("SECRET"),
            scopes);
        client.authorize();
        rounds = new Rounds(client,"906278647555784704");

    }
    @Test
    public void getRounds() {
        RoundsQueryBuilder roundsQuery = RoundsQuery.builder();
        Map<String,String> header = new HashMap<>();
        header.put("range","rounds=0-9");
        roundsQuery.groupId(986865420542550016L).groupId(987029336941142023L)
        .stageId(987313089934254080L).stageId(906330006561030144L);
        List<Round> list = rounds.getRounds(roundsQuery.build(),header);
        System.out.println(list);
    }

    @Test
     public void getRoundByIDTest(){
        Round round = rounds.getRoundByID("986947781466259469");
        assertTrue(round.getStageID().matches("906330006561030144"));
        assertTrue(round.getGroupID().matches("986865420542550016"));
    }
}
