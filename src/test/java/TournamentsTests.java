import static org.junit.Assert.*;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.concepts.Tournaments;
import ch.wisv.toornament.model.*;
import ch.wisv.toornament.model.enums.MatchFormat;
import ch.wisv.toornament.model.enums.ParticipantType;
import ch.wisv.toornament.model.request.TournamentRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TournamentsTests {
    private ToornamentClient client;
    private ToornamentClient client1;
    //@Mock
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
        tournamentDetails.setName("OWL Season 1 TEST");
        tournamentDetails.setSize(144);
        tournamentDetails.setDiscipline("overwatch");

        tournamentRequest.setName("OWL Season 1 TEST");
        tournamentRequest.setDiscipline("overwatch");
        tournamentRequest.setOrganization("Blizzard Entertainment");
        tournamentRequest.setWebsite("http://www.overwatchleague.com");
        tournamentRequest.setMatchFormat(MatchFormat.BO3);
        tournamentRequest.setIsPublic(false);
        tournamentRequest.setPrize("1 - $10,000 \n 2 - $5,000");
        tournamentRequest.setSize(144);
        tournamentRequest.setParticipantType(ParticipantType.TEAM);
        tournamentRequest.setTimezone("America/Los_Angeles");
        tournamentRequest.setCountry("US");
        tournamentRequest.setDateStart(LocalDate.parse("2018-09-05",DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        tournamentRequest.setDateEnd(LocalDate.parse("2019-04-05",DateTimeFormatter.ofPattern("yyyy-MM-dd")));


    }

    @Test
    public void getFeaturedTournamentsTest() {
            headers.put("range","tournaments=0-49");
            List<Tournament> details = client.tournamentsV2().getFeaturedTournaments(params,headers);

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

        Tournament tournament = client.tournamentsV2().getTournament("906278647555784704");
        assertEquals("overwatch",tournament.getDiscipline());
    }

    @Test //need new tournament id with streams
    public void getStreamsTest(){
        Map<String,String> range = new HashMap<>();
        range.put("range","streams=0-5");
        List<Stream> streams = client.tournamentsV2().getStreams("906278647555784704", range);
        System.out.println(streams);
        assertEquals(4,streams.size());
    }

    @Test
    public void getVodsTest(){
        List<Vod> vods = client.tournaments().getVods("906278647555784704");
        System.out.println(vods);
        assertNotNull(vods);
        assertFalse(vods.isEmpty());
    }

    @Test
    public void getAllTournamentsTest(){
        List<Tournament> list = client.tournaments().getAllTournaments();
        assertTrue(!list.isEmpty());
    }

    @Test
    public void getTournamentByDisciplineTest(){
        List<Tournament> list = client.tournaments().getTournamentByDiscipline("overwatch");
        assertFalse(list.isEmpty());
    }

    @Test
    public void createTournamentTest(){
    client.tournaments().createTournament(tournamentRequest);
    }

    @Test
    public void deleteTournamentTest(){
       List<Tournament> list = client.tournaments().getMyTournaments();
        for(Tournament t : list)
       client.tournaments().deleteTournament(t.getId());

       assertTrue(client.tournaments().getMyTournaments().isEmpty());
    }

    @Test
    public void StagesTest(){
        List<Stage> list = client.tournaments().getStages("906278647555784704");
        assertFalse(list.isEmpty());
        assertEquals(6,list.size());

    }

}
