import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.concepts.RoundsV2;
import ch.wisv.toornament.model.Round;
import ch.wisv.toornament.model.enums.Scope;
import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

 public class RoundsV2Test {
    private ToornamentClient client;
     private HashSet<Scope> scopes = new HashSet<>();
    private RoundsV2 rounds;
    @Before
    public void Setup() throws IOException {
        scopes.add(Scope.ORGANIZER_ADMIN);
        client = new ToornamentClient(System.getenv("KEY"), System.getenv("CLIENT"), System.getenv("SECRET"),
            scopes);
        client.authorize();
        rounds = new RoundsV2(client,"906278647555784704");

    }
    @Test
    public void getRounds() {
        Map<String,String> params = new HashMap<>();
        Map<String,String> header = new HashMap<>();
        header.put("range","rounds=0-49");
        params.put("group_ids","986865420542550016,987029336941142023");
        params.put("stage_ids","987313089934254080,906330006561030144");
        System.out.println(params);

        List<Round> list = rounds.getRounds(params,header);
        System.out.println(list);
    }

    @Test
     public void getRoundByIDTest(){
        Round round = rounds.getRoundByID("986947781466259469");
        assertTrue(round.getStage_id().matches("906330006561030144"));
        assertTrue(round.getGroup_id().matches("986865420542550016"));
    }
}
