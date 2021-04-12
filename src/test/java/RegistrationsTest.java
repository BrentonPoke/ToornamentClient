import com.toornament.ToornamentClient;
import com.toornament.concepts.Registrations;
import com.toornament.model.Custom.CustomFields;
import com.toornament.model.RegisteredParticipant;
import com.toornament.model.Registration;
import com.toornament.model.TournamentDetails;
import com.toornament.model.enums.RegistrationType;
import com.toornament.model.enums.Scope;
import com.toornament.model.header.RegistrationsHeader;
import com.toornament.model.request.RegistrationQuery;
import com.toornament.model.request.RegistrationQuery.RegistrationQueryBuilder;
import java.util.HashSet;
import java.util.List;
import lombok.SneakyThrows;
import org.json.JSONObject;
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
    RegistrationsHeader headers = new RegistrationsHeader().build(0,49);
    String registrationID = "";
  @Before
  public void Setup() {
    scopes.add(Scope.ORGANIZER_REGISTRATION);
    scopes.add(Scope.ORGANIZER_PARTICIPANT);
    client =
        new ToornamentClient(
            System.getenv("KEY"), System.getenv("CLIENT"), System.getenv("SECRET"), scopes);

        }
    @Test
    public void Registration(){
            Registrations registration = client.registrations("4488596999271456768");
            RegistrationQueryBuilder query = RegistrationQuery.builder();
            CustomFields fields = new CustomFields();
            fields.setInstagram("brenton_poke");
            fields.setTwitter("@brentonpoke");

            RegisteredParticipant participant = new RegisteredParticipant();
            participant.setName("Terry");
            participant.setCustomUserIdentifier("Batsuit");
            participant.setEmail("McGinnis@gotham.gov");
            query.type(RegistrationType.TEAM)
                .name("Batman")
                .email("batman@gotham.gov")
                .customFields(fields)
//                .customField("discord","Nightwing#1564")
//                .customField("slack","batman@gotham.gov")
//                .customField("instagram","McGinnis")
                .lineup(participant)
                .tournamentID("4488596999271456768");

            registration.register(query.build());

        }

    @SneakyThrows
    @Test
    public void getRegistrationsTest(){
            Registrations registrations = client.registrations("4488596999271456768");

        List<Registration> participants = registrations.getRegistrations(headers);
            logger.debug("participants: {}",participants);
            logger.debug(participants.get(0).getId());


        Assert.assertEquals("batman@gotham.gov", participants.get(0).getEmail());
    JSONObject jsonArray =
        new JSONObject(
            "{\"name\":\"Terry\",\"custom_fields\":{},\"custom_user_identifier\":\"Batsuit\"}");
        JSONAssert.assertEquals(jsonArray.toString(),participants.get(0).getLineup().get(0).toString(),false);
        }
    @Test
    public void deleteRegistrationsTest(){
    Registrations registrations = client.registrations("4488596999271456768");
        List<Registration> participants = registrations.getRegistrations(headers);
        this.registrationID = participants.get(0).getId();
        Assert.assertEquals(204, registrations.deleteRegistration(registrationID));

}

}
