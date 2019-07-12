import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.concepts.ParticipantsV2;
import ch.wisv.toornament.model.Participant;
import ch.wisv.toornament.model.TeamParticipant;
import ch.wisv.toornament.model.enums.Scope;
import ch.wisv.toornament.model.enums.Sort;
import ch.wisv.toornament.model.request.ParticipantQuery;
import ch.wisv.toornament.model.request.ParticipantQuery.ParticipantQueryBuilder;
import java.util.HashSet;
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
    private HashSet<Scope> scopes = new HashSet<>();
    @Before
    public void Setup() throws IOException {
        scopes.add(Scope.MANAGE_PARTICIPANTS);
        scopes.add(Scope.USER_INFO);
        client = new ToornamentClient(System.getenv("KEY"), System.getenv("CLIENT"), System.getenv("SECRET"),
            scopes);
        client.authorize();
    }
    @Test
    public void getTeamParticipants() {
        participants = new ParticipantsV2(client, "906278647555784704");
        Map<String,String> header = new HashMap<>();
        header.put("range"," participants=0-49");
        ParticipantQueryBuilder params = ParticipantQuery.builder();
        params.name("Outlaws");
        System.out.println(params.build());
        List<TeamParticipant> participantList = participants.getTeamParticipants(header,params.build());
        System.out.println(participantList);
        Assert.assertNotNull(participantList);
        assertTrue(participantList.get(0).getId().matches("906362615269785600"));

    }
    @Test
    public void getParticipants() {
        participants = new ParticipantsV2(client,"1065246192351223808");
        Map<String,String> header = new HashMap<>();
        header.put("range"," participants=0-49");
        ParticipantQueryBuilder params = ParticipantQuery.builder();
        params.name("Ant").sort(Sort.ALPHABETIC);

        List<Participant> participantList = participants.getParticipants(header,params.build());

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
        Participant team = participants.getParticipant("906362615269785600");
        assertTrue(team.getName().matches("A. Houston Outlaws"));
    }
}
