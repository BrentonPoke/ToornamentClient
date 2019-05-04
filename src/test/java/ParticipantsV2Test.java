import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.concepts.ParticipantsV2;
import ch.wisv.toornament.model.Participant;
import ch.wisv.toornament.model.TeamParticipant;
import ch.wisv.toornament.model.enums.Scope;
import ch.wisv.toornament.model.enums.Sort;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ParticipantsV2Test {
    private ToornamentClient client;
    private ParticipantsV2 participants;
    private Participant participant;
    @Before
    public void Setup() throws IOException {
        client = new ToornamentClient(System.getenv("KEY"), System.getenv("CLIENT"), System.getenv("SECRET"),
            Scope.ORGANIZER_ADMIN);
        client.authorize();
    }
    @Test
    public void getTeamParticipants() {
        participants = new ParticipantsV2(client, "906278647555784704");
        Map<String,String> header = new HashMap<>();
        header.put("range"," participants=0-49");
        Map<String,String> params = new HashMap<>();
        params.put("name","Outlaws");
        params.put("sort",String.valueOf(Sort.created_desc));
        List<TeamParticipant> participantList = participants.getTeamParticipants(header,params);
        System.out.println(participantList);
        Assert.assertNotNull(participantList);
        assertTrue(participantList.get(0).getId().matches("906362615269785600"));

    }
    @Test
    public void getParticipants() {
        participants = new ParticipantsV2(client,"1065246192351223808");
        Map<String,String> header = new HashMap<>();
        header.put("range"," participants=0-49");
        Map<String,String> params = new HashMap<>();
        params.put("name","Ant");
        params.put("sort",String.valueOf(Sort.alphabetic));
        List<Participant> participantList = participants.getParticipants(header,params);

        assertNotNull(participantList);
        assertTrue(participantList.get(0).getName().matches("Ant"));
        assertTrue(participantList.get(0).getId().matches("1065253210852368384"));

    }

    @Test
    public void getParticipantTest() {
        participants = new ParticipantsV2(client,"1065246192351223808");
        participant = participants.getParticipant("1065253210852368384");
        assertTrue(participant.getName().matches("Ant"));
    }

    @Test
    public void getTeamParticipantTest(){
        participants = new ParticipantsV2(client, "906278647555784704");
        TeamParticipant team = participants.getTeamParticipant("906362615269785600");
        assertTrue(team.getName().matches("A. Houston Outlaws"));
    }
}
