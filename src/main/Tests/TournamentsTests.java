import static org.junit.Assert.*;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.model.Tournament;
import ch.wisv.toornament.model.TournamentDetails;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class TournamentsTests {
    private ToornamentClient client;
    HashMap<String,String> params;
    HashMap<String,String> headers;

    @Before
    public void Setup() throws IOException {
        Path path = Paths.get("src/main/Tests/keys.txt");
        Stream<String> streamOfStrings = Files.lines(path);
        Stream<String> streamWithCharset =
            Files.lines(path, Charset.forName("UTF-8"));
        Object[] keys = streamWithCharset.toArray();
        client = new ToornamentClient(keys[0].toString(),keys[1].toString(),keys[2].toString());
        client.authorize();
        headers = new HashMap<>();
        params = new HashMap<>();
        params.put("disciplines","overwatch");
    }
    @AfterEach
    public void CleanUp(){
        headers.clear();
    }

    @Test
    public void getFeaturedTournamentsTest() {
            headers.put("range","tournaments=0-49");
            List<Tournament> details = client.tournaments().getFeaturedTournaments(params,headers);
        //    for (Tournament t : details)
       // System.out.println(t.getName());

        ArrayList<Tournament> list = new ArrayList<>(details);

                assertTrue(!list.isEmpty());
    }

    @Test
    public void getTournamentsWithParamsTest(){
        List<Tournament> details = client.tournaments().getTournamentsWithParams(params);
        assertTrue(!details.isEmpty());
    }

    @Test
    public void getTournamentTest(){
        TournamentDetails tournament = client.tournaments().getTournament("1257784630743515136");

            System.out.println(tournament.toString());
        assertEquals("overwatch",tournament.getDiscipline());
    }

    @Test
    public void getAllTournamentsTest(){
        List<Tournament> list = client.tournaments().getAllTournaments();
        assertTrue(!list.isEmpty());
    }

}
