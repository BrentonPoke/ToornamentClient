import com.toornament.ToornamentClient;
import com.toornament.concepts.Registrations;
import com.toornament.model.Custom.CustomFields;
import com.toornament.model.RegisteredParticipant;
import com.toornament.model.Registration;
import com.toornament.model.TournamentDetails;
import com.toornament.model.enums.RegistrationType;
import com.toornament.model.enums.Scope;
import com.toornament.model.request.RegistrationQuery;
import com.toornament.model.request.RegistrationQuery.RegistrationQueryBuilder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationsTest {
    private ToornamentClient client;
    private TournamentDetails details = new TournamentDetails();
    private HashSet<Scope> scopes = new HashSet<>();
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    String registrationID = "";
  @Before
  public void Setup() {
    scopes.add(Scope.ORGANIZER_REGISTRATION);
    scopes.add(Scope.ORGANIZER_PARTICIPANT);
    client =
        new ToornamentClient(
            System.getenv("KEY"), System.getenv("CLIENT"), System.getenv("SECRET"), scopes);
    client.authorize();

        }
    @Test
    public void Registration(){
            Registrations registration = client.registrations("3563800755903569920");
            RegistrationQueryBuilder query = RegistrationQuery.builder();
            CustomFields fields = new CustomFields();
            fields.setInstagram("brenton_poke");
            fields.setTwitter("@brentonpoke");

            RegisteredParticipant participant = new RegisteredParticipant();
            participant.setName("Terry");
            participant.setCustomUserIdentifier("Batsuit");
            query.type(RegistrationType.TEAM)
                .name("Batman")
                .email("McGinnis@gotham.gov")
                .customFields(fields)
//                .customField("discord","Nightwing#1564")
//                .customField("slack","batman@gotham.gov")
//                .customField("instagram","McGinnis")
                .lineup(participant)
                .tournamentID("3563800755903569920");

            registration.register(query.build());

        }

    @SneakyThrows
    @Test
    public void getRegistrationsTest(){
            Registrations registrations = client.registrations("3563800755903569920");
            HashMap headers = new HashMap();
            headers.put("range","registrations=0-49");
        List<Registration> participants = registrations.getRegistrations(headers);
            logger.debug("participants: {}",participants);
            logger.debug(participants.get(0).getId());


        Assert.assertEquals("McGinnis@gotham.gov", participants.get(0).getEmail());

        JSONArray jsonArray = new JSONArray("[{\"name\":\"Terry\",\"custom_fields\":[],\"custom_user_identifier\":\"Batsuit\"}]");
        JSONAssert.assertEquals(jsonArray.toString(),participants.get(0).getLineup().toString(),false);
        }
    @Test
    public void deleteRegistrationsTest(){
    Registrations registrations = client.registrations("3563800755903569920");
        HashMap headers = new HashMap();
        headers.put("range","registrations=0-49");
        List<Registration> participants = registrations.getRegistrations(headers);
        this.registrationID = participants.get(0).getId();
        Assert.assertEquals(204, registrations.deleteRegistration(registrationID));

}

}
