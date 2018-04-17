import static org.junit.Assert.*;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.concepts.Tournaments;
import ch.wisv.toornament.model.Tournament;
import ch.wisv.toornament.model.TournamentDetails;
import ch.wisv.toornament.model.enums.MatchFormat;
import ch.wisv.toornament.model.enums.ParticipantType;
import ch.wisv.toornament.model.request.TournamentRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.io.IOException;
import java.util.*;

public class TournamentsTests {
    private ToornamentClient client;

    Tournaments caller = Mockito.mock(Tournaments.class);
    private HashMap<String,String> params;
    private HashMap<String,String> headers;

    private TournamentRequest tournamentRequest = new TournamentRequest();
    private TournamentDetails tournamentDetails = new TournamentDetails();
    @Before
    public void Setup() throws IOException {
        client = new ToornamentClient(System.getenv("KEY"),System.getenv("CLIENT"),System.getenv("SECRET"));
        client.authorize();

        headers = new HashMap<>();
        params = new HashMap<>();
        params.put("disciplines","overwatch");

        tournamentDetails.setParticipantType(ParticipantType.TEAM);
        tournamentDetails.setName("OWL Season 1");
        tournamentDetails.setSize(144);
        tournamentDetails.setDiscipline("overwatch");

        tournamentRequest.setDiscipline("overwatch");
        tournamentRequest.setOrganization("Blizzard Entertainment");
        tournamentRequest.setWebsite("http://www.overwatchleague.com");
        tournamentRequest.setMatchFormat(MatchFormat.BO3);
        tournamentRequest.setPrize("$500,000-$1,000,000");
        tournamentRequest.setSize(144);
        tournamentRequest.setName("OWL Season 1");
        tournamentRequest.setParticipantType(ParticipantType.TEAM);

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

        TournamentDetails tournament = client.tournaments().getTournament("1257784630743515136");
        assertEquals("overwatch",tournament.getDiscipline());
    }

    @Test
    public void getAllTournamentsTest(){
        List<Tournament> list = client.tournaments().getAllTournaments();
        assertTrue(!list.isEmpty());
    }

    @Test
    public void createTournamentTest(){
       Mockito.when(caller.createTournament(tournamentRequest)).thenReturn(tournamentDetails);

    }

}
