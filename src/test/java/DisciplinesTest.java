import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.concepts.Disciplines;
import ch.wisv.toornament.model.Discipline;
import ch.wisv.toornament.model.DisciplineDetails;
import ch.wisv.toornament.model.enums.Scope;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


import java.io.IOException;
import java.util.List;

public class DisciplinesTest {
    private ToornamentClient client;
    private Disciplines disciplines;
    @Before
    public void Setup() throws IOException {
        client = new ToornamentClient(System.getenv("KEY"), System.getenv("CLIENT"), System.getenv("SECRET"),Scope.ORGANIZER_VIEW);
        client.authorize();
        disciplines = new Disciplines(client);
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
