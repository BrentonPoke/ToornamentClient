import com.toornament.ToornamentClient;
import com.toornament.concepts.DisciplinesV2;
import com.toornament.model.Discipline;
import com.toornament.model.DisciplineDetails;
import com.toornament.model.enums.Scope;
import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


import java.io.IOException;
import java.util.List;

public class DisciplinesTest {
    private ToornamentClient client;
    private DisciplinesV2 disciplines;
    private HashSet<Scope> scopes = new HashSet<>();
    @Before
    public void Setup() throws IOException {
        scopes.add(Scope.ORGANIZER_VIEW);
        client = new ToornamentClient(System.getenv("KEY"), System.getenv("CLIENT"), System.getenv("SECRET"),scopes);
        client.authorize();
        disciplines = new DisciplinesV2(client);
    }

    @Test
    public void getDisciplines() {
        List<Discipline> list = disciplines.getDisciplines("disciplines=200-249");
    System.out.println(list);

        assertTrue(!list.isEmpty());

    }

    @Test
    public void getDiscipline() {
        DisciplineDetails details = disciplines.getDiscipline("smite");
        assertTrue(details.getId().matches("smite"));
    }
}
