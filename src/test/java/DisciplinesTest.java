import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.concepts.Disciplines;
import ch.wisv.toornament.model.Discipline;
import ch.wisv.toornament.model.DisciplineDetails;
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
        client = new ToornamentClient(System.getenv("KEY"), System.getenv("CLIENT"), System.getenv("SECRET"));
        client.authorize();
        disciplines = new Disciplines(client);
    }

    @Test
    public void getDisciplines() {
        List<Discipline> list = disciplines.getDisciplines();

        assertTrue(!list.isEmpty());

    }

    @Test
    public void getDiscipline() {
        DisciplineDetails details = disciplines.getDiscipline("overwatch");
        assertTrue(details.getID().matches("overwatch"));
    }
}
