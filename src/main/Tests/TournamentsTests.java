import static org.junit.Assert.*;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.concepts.Tournaments;
import ch.wisv.toornament.model.Tournament;
import ch.wisv.toornament.model.request.ApiTokenRequest;
import okhttp3.Response;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TournamentsTests {
    private ToornamentClient client;
    private Response response;

    @Before
    public void Setup(){
        client = new ToornamentClient("yEF4GKOHO6MDYWh4q_6u0mHO5KfEVu1gAN20Dr76GtI",
            "58ff4401140ba08e7f8b4567269ltppwn480ggw08gc4ggkcccwsgsog4cssc80c8swgkwg0so",
            "nyfrwu8nsfko8cwckgwco840csc0k4wcog0gw84gwo440gggg");
        client.authorize();
    }

    @Test
    public void getAllTournamentsTest() {
        try {
            List<Tournament> details = client.tournaments().getAllTournaments();

            Iterator<Tournament> iterator = details.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next().getName());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
