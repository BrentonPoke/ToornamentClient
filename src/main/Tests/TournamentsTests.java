import static org.junit.Assert.*;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.model.Tournament;
import ch.wisv.toornament.model.TournamentDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class TournamentsTests {
    private ToornamentClient client;
    private HashMap<String,String> params;
    private HashMap<String,String> headers;

    @Before
    public void Setup() throws IOException {
//        Path path = Paths.get("src/main/Tests/keys.txt");
//        Stream<String> streamWithCharset =
//            Files.lines(path, Charset.forName("UTF-8"));
//        Object[] keys = streamWithCharset.toArray();
//        client = new ToornamentClient(keys[0].toString(),keys[1].toString(),keys[2].toString());
        client = new ToornamentClient(System.getenv("KEY"),System.getenv("CLIENT"),System.getenv("SECRET"));
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
        System.out.println(System.getenv("CLIENT"));
        System.getenv("secret");
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
