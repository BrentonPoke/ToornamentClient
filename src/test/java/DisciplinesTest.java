import com.toornament.ToornamentClient;
import com.toornament.concepts.Disciplines;
import com.toornament.model.Discipline;
import com.toornament.model.DisciplineDetails;
import com.toornament.model.enums.Scope;
import com.toornament.model.header.DisciplinesHeader;
import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


import java.io.IOException;
import java.util.List;

public class DisciplinesTest {
    private ToornamentClient client;
    private Disciplines disciplines;
    private DisciplinesHeader disciplineHeader;
    private HashSet<Scope> scopes = new HashSet<>();
    @Before
    public void Setup() throws IOException {
        scopes.add(Scope.ORGANIZER_RESULT);
        client = new ToornamentClient(System.getenv("KEY"), System.getenv("CLIENT"), System.getenv("SECRET"),scopes);
        disciplines = client.disciplines();
        disciplineHeader = new DisciplinesHeader();
    }

    @Test
    public void getDisciplines() {
        List<Discipline> list = disciplines.getDisciplines(disciplineHeader.build(200,249));
    System.out.println(list);

        assertFalse(list.isEmpty());

    }

    @Test
    public void getDiscipline() {
        DisciplineDetails details = disciplines.getDiscipline("smite");
        assertTrue(details.getId().matches("smite"));
    }
}
