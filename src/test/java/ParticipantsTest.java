import com.toornament.ToornamentClient;
import com.toornament.concepts.Participants;
import com.toornament.model.Participant;
import com.toornament.model.enums.Scope;
import com.toornament.model.enums.Sort;
import com.toornament.model.header.ParticipantsHeader;
import com.toornament.model.request.ParticipantQuery;
import com.toornament.model.request.ParticipantQuery.ParticipantQueryBuilder;
import java.util.HashSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class ParticipantsTest {
    private ToornamentClient client;
    private Participants participants;
    private Participant participant;
    private HashSet<Scope> scopes = new HashSet<>();
    ParticipantsHeader header = new ParticipantsHeader();
    @Before
    public void Setup() throws IOException {
        scopes.add(Scope.ORGANIZER_PARTICIPANT);
        scopes.add(Scope.ORGANIZER_RESULT);
        client = new ToornamentClient(System.getenv("KEY"), System.getenv("CLIENT"), System.getenv("SECRET"),
            scopes);

        header.build(0,49);
    }
    @Test
    public void getTeamParticipants() {
        participants = client.participants("906278647555784704");

        ParticipantQueryBuilder params = ParticipantQuery.builder();
        params.name("Outlaws");
        //System.out.println(params.build());
        List<Participant> participantList = participants.getTeamParticipants(header,params.build());
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.debug("Participant list: {}",participantList);
        Assert.assertNotNull(participantList);
        assertTrue(participantList.get(0).getId().matches("906362615269785600"));

    }
    @Test
    public void getParticipants() {
        participants = new Participants(client,"1065246192351223808");

        ParticipantQueryBuilder params = ParticipantQuery.builder();
        params.name("Ant").sort(Sort.ALPHABETIC);

        List<Participant> participantList = participants.getParticipants(header,params.build());

        assertNotNull(participantList);
        assertTrue(participantList.get(0).getName().matches("Ant"));
        assertTrue(participantList.get(0).getId().matches("1065253210852368384"));

    }

    @Test
    public void getParticipantTest() {
        participants = new Participants(client,"1065246192351223808");
        participant = participants.getParticipant("1065253210852368384");
        assertTrue(participant.getName().matches("Ant"));
    }

    @Test
    public void getTeamParticipantTest(){
        participants = new Participants(client, "906278647555784704");
        Participant team = participants.getParticipant("906362615269785600");
        assertTrue(team.getName().matches("A. Houston Outlaws"));
    }
}
